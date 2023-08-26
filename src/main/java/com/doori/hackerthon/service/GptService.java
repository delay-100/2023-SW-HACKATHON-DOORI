package com.doori.hackerthon.service;

import com.doori.hackerthon.dto.Chat;
import com.doori.hackerthon.dto.ChatResponse;
import com.doori.hackerthon.entity.ChatMessage;
import com.doori.hackerthon.repository.mongo.ChatMessageRepository;
import dev.langchain4j.chain.ConversationalRetrievalChain;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.SentenceSplitter;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.huggingface.HuggingFaceEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatRequest;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static dev.langchain4j.data.document.FileSystemDocumentLoader.loadDocument;
import static dev.langchain4j.model.huggingface.HuggingFaceModelName.SENTENCE_TRANSFORMERS_ALL_MINI_LM_L6_V2;
import static dev.langchain4j.model.huggingface.HuggingFaceModelName.TII_UAE_FALCON_7B_INSTRUCT;
import static java.time.Duration.ofSeconds;
import static dev.langchain4j.model.openai.OpenAiModelName.TEXT_EMBEDDING_ADA_002;
@Service
@RequiredArgsConstructor
public class GptService {
    private final ChatgptService chatgptService;
    private final ChatMessageRepository chatMessageRepository;
    @Value("${chatgpt.api-key}")
    public String OPENAI_API_KEY;


    public String callPythonAPI() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://python-api-url"); // Python API의 엔드포인트 URL로 변경
            HttpResponse response = httpClient.execute(request);

            // 응답 데이터 처리
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return responseBody;
            } else {
                // 오류 처리
                return "Error: " + response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public ChatResponse getChatResponse(Chat prompt) {
        String role = "1. You are a kind teacher who extracts important keywords from students' sentences and asks questions. \\n2. If there is something missing about what I said, don't explain and ask about the missing words.\\n 3. If I say something conceptually wrong, please correct me and continue the question. \\n4. If the current concept is correct, ask back-to-back questions related to the current concept. \\n5. If the student answers with the nuance of 'I don't know', please re-question by changing the question to make it easier to understand. \\n6. Repeat the same question up to 4 times.\\n7. Speaks in Korean.";
//        System.out.println(prompt.length());

//        // ChatGPT 에게 질문을 던집니다.
        MultiChatMessage message1 = new MultiChatMessage();
        message1.setRole("system");
        message1.setContent(role);

        MultiChatMessage message2 = new MultiChatMessage();
        message2.setRole("user");
        message2.setContent(prompt.getMessage());

        List<MultiChatMessage> list = new ArrayList<>();
        list.add(message1);
        list.add(message2);

        MultiChatRequest chatRequest = new MultiChatRequest();
        chatRequest.setModel("gpt-3.5-turbo");
        chatRequest.setMessages(list);

        List<ChatMessage> messages = chatMessageRepository.findTop2ByUserId(prompt.getUserId());

        if(messages.size() != 0){
            for(ChatMessage message : messages){
                MultiChatMessage m = new MultiChatMessage();
                m.setContent(message.getAi());
                m.setRole("assistant");
                list.add(m);
            }
        }
        MultiChatResponse response = chatgptService.multiChatRequest(chatRequest);
        ChatResponse answer = new ChatResponse();
        answer.setMessage(String.valueOf(response.getChoices().get(0).getMessage().getContent()));
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAi(answer.getMessage());
        chatMessage.setUser(prompt.getMessage());
        chatMessage.setUserId(prompt.getUserId());
        chatMessageRepository.save(chatMessage);
        return answer;
    }
    public ChatResponse getChatDocument(Chat prompt){
        Document document = loadDocument(toPath("/data/data.txt"));
        System.out.println(OPENAI_API_KEY);
        DocumentSplitter splitter = new SentenceSplitter();
        List<TextSegment> segments = splitter.split(document);
//        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
//                .apiKey(OPENAI_API_KEY)
//                .modelName(TEXT_EMBEDDING_ADA_002)
////                .timeout(ofSeconds(60))
//                .logRequests(true)
//                .logResponses(true)
//                .build();
//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
//        List<Embedding> embeddings = embeddingModel.embedAll(segments);

        HuggingFaceEmbeddingModel huggingFaceEmbeddingModel = HuggingFaceEmbeddingModel.builder()
                .accessToken("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")
                .modelId(SENTENCE_TRANSFORMERS_ALL_MINI_LM_L6_V2)
//                .modelId("sentence-transformers/paraphrase-multilingual-MiniLM-L12-v2")
                .timeout(ofSeconds(600))
                .build();

        List<Embedding> embeddings = huggingFaceEmbeddingModel.embedAll(segments);
        // Store embeddings into embedding store for further search / retrieval
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);
        ConversationalRetrievalChain chain = ConversationalRetrievalChain.builder()
                .chatLanguageModel(OpenAiChatModel.withApiKey(OPENAI_API_KEY))
                .retriever(EmbeddingStoreRetriever.from(embeddingStore, huggingFaceEmbeddingModel))
                // .chatMemory() // you can override default chat memory
                // .promptTemplate() // you can override default prompt template
                .build();
        ChatResponse answer = new ChatResponse();
        answer.setMessage(chain.execute(prompt.getRole()));
        System.out.println(answer); // Charlie is a cheerful carrot living in VeggieVille...
        return answer;
    }
    private static Path toPath(String fileName) {
        try {
            URL fileUrl = GptService.class.getResource(fileName);
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
