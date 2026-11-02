package com.substring.helpdesk.service;

import com.substring.helpdesk.tools.EmailTool;
import com.substring.helpdesk.tools.TicketDatabaseTool;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;
    private final TicketDatabaseTool ticketDatabaseTool;
    private final EmailTool emailTool;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPromptResource;

//    basic call to llm
    public String getResponseFromAssistant(String userQuery, String conversationId){
        return  this.chatClient
                .prompt()
                .tools(ticketDatabaseTool,emailTool)
                .system(systemPromptResource)
                .user(userQuery)
                .advisors(
                        advisorSpec -> advisorSpec
                                .param(ChatMemory.CONVERSATION_ID,conversationId)
                )
                .call()
                .content();
    }

    public Flux<String> streamResponseFromAssistant(String userQuery, String conversationId){
        return  this.chatClient
                .prompt()
                .advisors(
                        advisorSpec -> advisorSpec
                                .param(ChatMemory.CONVERSATION_ID,conversationId)
                )
                .tools(ticketDatabaseTool,emailTool)
                .system(systemPromptResource)
                .user(userQuery)
                .stream()
                .content();
    }

}
