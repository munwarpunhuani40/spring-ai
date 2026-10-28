package com.spring.ai.firstproject.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient chatClient){
       this.chatClient = chatClient;
    }

    @Override
    public String chat(String query) {
//       String prompt = "tell me about Sachin Tandulkar";
//        return chatClient.prompt().user(prompt).system("As expert in cricket").call().content();
//        Prompt prompt1 = new Prompt(query, OpenAiChatOptions.builder()
//                .model("gpt-4o-mini")
//                .temperature(0.2)
//                .maxTokens(100)
//                .build());
        Prompt prompt1 = new Prompt(query);
        String queryStr = "As an expert in coding and programming. Always write program in java. Now" +
                "reply for this question: {query}";
        System.out.println(chatClient.prompt(prompt1).call().chatResponse().getMetadata());

        var content = chatClient
                .prompt(prompt1)
                .user(u-> u.text(queryStr).param("query",query))
                .call()
                .content();

        return content;
    }
}
