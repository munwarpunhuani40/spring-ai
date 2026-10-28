package com.spring.ai.firstproject.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

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

    public String chatTemplate(){
//      first step
//        PromptTemplate strTemplate = PromptTemplate.builder().template("What is {techName}? and also tell me about {techExample} ").build();
//
////      render the template
//        String renderedMessage = strTemplate.render(Map.of(
//                "techName", "spring",
//                "techExample", "Spring Exception"
//        ));
//
//        Prompt  prompt = new Prompt(renderedMessage);

//        one way is prompt Templating

//        var systemPromptTemplate = SystemPromptTemplate.builder()
//                .template("You are a helpful coding assistant. You are an expert in coding.")
//                .build();
//        var systemMessage = systemPromptTemplate.createMessage();
//
//        var userPromptTemplate = PromptTemplate.builder().template("What is {techName}? and also tell me about {techExample} ").build();
//        var userMessage = userPromptTemplate.createMessage(Map.of(
//                "techName","Spring",
//                "techExample","Spring Exception"
//        ));
//
//        Prompt prompt = new Prompt(systemMessage,userMessage);


//        2nd way is ChatClient Fluent API


        return this.chatClient
                .prompt()
                .system(system -> system.text(this.systemMessage))
                .user(user ->
                        user.text(userMessage).param("concept","python iteration"))
                .call()
                .content();
    }
}
