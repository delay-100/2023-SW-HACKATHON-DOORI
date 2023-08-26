//package com.doori.hackerthon.util;
//
//import dev.langchain4j.chain.Chain;
//import dev.langchain4j.data.message.AiMessage;
//import dev.langchain4j.data.message.UserMessage;
//import dev.langchain4j.data.segment.TextSegment;
//import dev.langchain4j.memory.ChatMemory;
//import dev.langchain4j.memory.chat.MessageWindowChatMemory;
//import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.input.PromptTemplate;
//import dev.langchain4j.retriever.Retriever;
//import io.github.flashvayne.chatgpt.service.ChatgptService;
//import lombok.Builder;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static dev.langchain4j.internal.ValidationUtils.ensureNotBlank;
//import static dev.langchain4j.internal.ValidationUtils.ensureNotNull;
//import static java.util.stream.Collectors.joining;
//
//public class RetrievalChainImpl implements Chain<String, String> {
//
//    private static final PromptTemplate DEFAULT_PROMPT_TEMPLATE = PromptTemplate.from(
//            "Answer the following question to the best of your ability: {{question}}\n" +
//                    "\n" +
//                    "Base your answer on the following information:\n" +
//                    "{{information}}");
//    private final ChatLanguageModel chatLanguageModel;
//    private final ChatMemory chatMemory;
//    private final PromptTemplate promptTemplate;
//    private final Retriever<TextSegment> retriever;
//    private final ChatgptService chatgptService;
//
//    @Builder
//    public RetrievalChainImpl(ChatLanguageModel chatLanguageModel,
//                              ChatMemory chatMemory,
//                              PromptTemplate promptTemplate,
//                              Retriever<TextSegment> retriever, ChatgptService chatgptService) {
//        this.chatLanguageModel = ensureNotNull(chatLanguageModel, "chatLanguageModel");
//        this.chatMemory = chatMemory == null ? MessageWindowChatMemory.withMaxMessages(10) : chatMemory;
//        this.promptTemplate = promptTemplate == null ? DEFAULT_PROMPT_TEMPLATE : promptTemplate;
//        this.retriever = ensureNotNull(retriever, "retriever");
//        this.chatgptService = chatgptService;
//    }
//
//
//    @Override
//    public String execute(String question) {
//        question = ensureNotBlank(question, "question");
//
//        List<TextSegment> relevantSegments = retriever.findRelevant(question);
//
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("question", question);
//        variables.put("information", format(relevantSegments));
//
//        UserMessage userMessage = promptTemplate.apply(variables).toUserMessage();
//
//        chatMemory.add(userMessage);
//
//        AiMessage answer = chatLanguageModel.sendMessages(chatMemory.messages());
//
//        chatgptService.multiChatRequest(chatMemory.messages());
//        chatMemory.add(answer);
//
//        return answer.text();
//    }
//    private static String format(List<TextSegment> relevantSegments) {
//        return relevantSegments.stream()
//                .map(TextSegment::text)
//                .map(segment -> "..." + segment + "...")
//                .collect(joining("\n\n"));
//    }
//}
