package com.spring.ai.tool.calling.service;

import com.spring.ai.tool.calling.tool.SimpleDateTimeTool;
import com.spring.ai.tool.calling.tool.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private ChatClient chatClient;

    private WeatherTool weatherTool;

    public ChatService(ChatClient chatClient, WeatherTool weatherTool){
        this.chatClient = chatClient;
        this.weatherTool = weatherTool;
    }

    public String chat(String q){

        System.out.println("Getting Date");
        return chatClient
                .prompt()
                .tools(new SimpleDateTimeTool(),weatherTool)
                .user(q)
                .call()
                .content();
    }

}
