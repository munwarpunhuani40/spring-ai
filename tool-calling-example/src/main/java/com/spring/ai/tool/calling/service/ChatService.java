package com.spring.ai.tool.calling.service;

import com.spring.ai.tool.calling.tool.SimpleDateTimeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatClient chatClient;

    public ChatService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public String chat(String q){

        System.out.println("Getting Date");
        return chatClient
                .prompt()
                .tools(new SimpleDateTimeTool())
                .user(q)
                .call()
                .content();
    }

}
