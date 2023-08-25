# -*- coding: utf-8 -*-
from fastapi import FastAPI
import os
import sys
from langcorn.server.api import create_service
from langchain.chains import ConversationalRetrievalChain, RetrievalQA
from langchain.chat_models import ChatOpenAI
from langchain.document_loaders import DirectoryLoader, TextLoader
from langchain.embeddings import OpenAIEmbeddings
from langchain.indexes import VectorstoreIndexCreator
from langchain.indexes.vectorstore import VectorStoreIndexWrapper
from langchain.llms import OpenAI
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


os.environ["OPENAI_API_KEY"] = constants.APIKEY
host_url = constants.API_URL

app = FastAPI()
@app.post("/")
def retriever(system, user):
    client = MongoClient(host=host_url, port=9999, username = 'mongouser' , password= 'mypassword')
    # 데이터베이스와 컬렉션 선택
    db = client['HACKERTHON']
    collection = db['messages']

    # 가장 최신 메시지 2개 가져오기
    latest_messages = collection.find().sort('_id', -1).limit(10)
    history = []
    # print(latest_messages[0]["answer"])
    for i in latest_messages:
      history.append((i["user"], i["ai"]))

  # Enable to save to disk & reuse the model (for repeated queries on the same data)
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

    # embeddings = HuggingFaceEmbeddings()
    # index = VectorstoreIndexCreator(
    # vectorstore_cls=FAISS,
    # embedding=embeddings,
    # # text_splitter=text_splitter,
    # ).from_loaders([loader])
    # # print(docs)
    # index.vectorstore.save_local("faiss-nj")
    general_system_template = system
    general_user_template = user
    messages = general_system_template + general_user_template
    # print(index)

    retriever = ConversationalRetrievalChain.from_llm(
      llm=ChatOpenAI(model="gpt-3.5-turbo"),
      retriever=index.vectorstore.as_retriever(search_kwargs={"k": 1}),

    )
    # print(docs)
    chat_history = history
    result = retriever({"question" : messages,"chat_history": chat_history})
    # print(result)
    # chat_history.append((query, result['answer']))
    # collection.insert_one(result['answer'])
    answer = result['answer']
    document = {'user':messages,'ai': answer}
    collection.insert_one(document)
    return result["answer"]

# @app.post("/")
# def summarize(system:str, user:str):
