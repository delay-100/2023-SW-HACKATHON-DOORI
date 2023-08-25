# -*- coding: utf-8 -*-
from fastapi import FastAPI
import os
import sys
import langchain
from langchain.chains import ConversationalRetrievalChain, RetrievalQA
from langchain.chat_models import ChatOpenAI
from langchain.document_loaders import DirectoryLoader, TextLoader
from langchain.embeddings import OpenAIEmbeddings
from langchain.indexes import VectorstoreIndexCreator
from langchain.indexes.vectorstore import VectorStoreIndexWrapper
from langchain.llms import OpenAI
from langchain.schema import (
    AIMessage,
    HumanMessage,
    SystemMessage
)
from langchain.text_splitter import CharacterTextSplitter
from langchain.vectorstores import Chroma
from pymongo import MongoClient
####################################
#@title 9. Embeddings and VectorStore
from langchain.embeddings import HuggingFaceEmbeddings
from langchain.embeddings import OpenAIEmbeddings

####################################
from langchain.indexes import VectorstoreIndexCreator
from langchain.vectorstores import FAISS
import constants
from langchain.prompts.prompt import PromptTemplate
from langchain.chains.llm import LLMChain
from langchain import OpenAI, ConversationChain
from langchain.agents import load_tools
from langchain.agents import initialize_agent
from langchain.agents import AgentType
from langchain.vectorstores import Pinecone
import pinecone

os.environ["OPENAI_API_KEY"] = constants.CHATAPIKEY
os.environ["WOLFRAM_ALPHA_APPID"] = constants.WOLFRAMAPIKEY
os.environ["SERPAPI_API_KEY"] = constants.SERPAPIKEY
os.environ["PINECONE_API_KEY"] = constants.PINECONEAPIKEY
os.environ["PINECONE_ENV"] = constants.PINECONEENV

host_url = constants.API_URL


app = FastAPI()

@app.post("store")
def store_vector_text(subject_name):
  # initialize pinecone
  pinecone.init(
    api_key=os.getenv("PINECONE_API_KEY"),  # find at app.pinecone.io
    environment=os.getenv("PINECONE_ENV"),  # next to api key in console
  )
  index_name = "chatbot"
  # index가 없는 경우 새로 만든다.
  if index_name not in pinecone.list_indexes():
    # we create a new index
    pinecone.create_index(
      name=index_name,
      metric='cosine',
      dimension=1536
  )
  embeddings = OpenAIEmbeddings()
  # The OpenAI embedding model `text-embedding-ada-002 uses 1536 dimensions`
  loader = TextLoader("../resources/data/data.txt", encoding='utf-8') # Use this line if you only need data.txt
  documents = loader.load()
  text_splitter = CharacterTextSplitter(chunk_size=1000, chunk_overlap=0)
  docs = text_splitter.split_documents(documents)
  docsearch = Pinecone.from_documents(docs, embeddings, index_name=index_name)
  as_retriever = docsearch.as_retriever(search_type="mmr")

  llm=ChatOpenAI(model="gpt-3.5-turbo" ,streaming= True , temperature=1)
  retriever = ConversationalRetrievalChain.from_llm(
    llm=llm,
    retriever=as_retriever,
    )
  history = []
  result = retriever({"question" : "목차를 보여줘" , "chat_history": history})
  print(result["answer"])


@app.post("/")
def retriever(system, user):
    client = MongoClient(host=host_url, port=9999, username = 'mongouser' , password= 'mypassword')
    # 데이터베이스와 컬렉션 선택
    db = client['HACKERTHON']
    collection = db['messages']

    # 가장 최신 메시지 2개 가져오기
    latest_messages = collection.find().sort('_id', -1).limit(4)
    history = []
    # print(latest_messages[0]["answer"])
    for i in latest_messages:
      history.append((i["user"], i["ai"]))

    PERSIST = False

    query = None
    if len(sys.argv) > 1:
        query = sys.argv[1]

    if PERSIST and os.path.exists("persist"):
        print("Reusing index...\n")
        vectorstore = Chroma(persist_directory="persist", embedding_function=OpenAIEmbeddings())
        index = VectorStoreIndexWrapper(vectorstore=vectorstore)
    else:
      loader = TextLoader("../resources/data/data.txt", encoding='utf-8') # Use this line if you only need data.txt
        #  lo ader = DirectoryLoader("data/")
      if PERSIST:
          index = VectorstoreIndexCreator(vectorstore_kwargs={"persist_directory":"persist"}).from_loaders([loader])
      else:
          index = VectorstoreIndexCreator().from_loaders([loader])


    general_system_template = system
    general_user_template = user
    messages = general_system_template + general_user_template

    llm=ChatOpenAI(model="gpt-3.5-turbo" ,streaming= True , temperature=1)
    retriever = ConversationalRetrievalChain.from_llm(
      llm=llm,
      retriever=index.vectorstore.as_retriever(search_kwargs={"k": 1}),

    )
    print(retriever)
    chat_history = history

    result = retriever({"question" : messages, "chat_history": chat_history})
    answer = result['answer']
    document = {'user':messages,'ai': answer}
    collection.insert_one(document)
    return result["answer"]

@app.post("/chat")
def chate(system:str, user:str):
  chat = ChatOpenAI(model_name='gpt-3.5-turbo', temperature=0.9)
  sys = SystemMessage(content=system)
  msg = HumanMessage(content=user)

  aimsg = chat([sys, msg])
  print(aimsg.content)


@app.post("/chat2")
# 저장된 메시지를 사용한 일반 gpt-3.5-turbo 모델 사용
def conversation(system:str, user:str):

  chat = ChatOpenAI(model_name='gpt-3.5-turbo', temperature=0.9)

  client = MongoClient(host=host_url, port=9999, username = 'mongouser' , password= 'mypassword')
   # 데이터베이스와 컬렉션 선택
  db = client['HACKERTHON']
  collection = db['messages']

    # 가장 최신 메시지 2개 가져오기
  latest_messages = collection.find().sort('_id', -1).limit(2)
  print(latest_messages[0]['ai'])

  sys = SystemMessage(content=system)
  msg = HumanMessage(content=user)
  ai1 = AIMessage(content=latest_messages[0]['ai'])
  ai2 = AIMessage(content=latest_messages[1]['ai'])

  # document = {'user':messages,'ai': answer}
  # collection.insert_one(document)

  aimsg = chat([sys, msg, ai1 ,ai2])
  print("-------------content-------------------"+aimsg.content)


@app.post("/chat3")
def agentchat(user:str):
  langchain.debug = True
  chat = ChatOpenAI(model_name='gpt-3.5-turbo', temperature=0.9)
  tools = load_tools(["serpapi", "llm-math" , "wolfram-alpha"], llm=chat)
  agent = initialize_agent(tools, llm=chat, agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION, verbose=True)
  # sys = SystemMessage(content=system)
  msg = HumanMessage(content=user)
  # ai1 = AIMessage(content=latest_messages[0]['ai'])
  # ai2 = AIMessage(content=latest_messages[1]['ai'])
  return agent.run(input= msg)
  # print(result)
