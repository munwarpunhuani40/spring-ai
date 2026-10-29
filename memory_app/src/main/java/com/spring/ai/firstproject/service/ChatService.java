package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.entity.Tut;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ChatService {
//    String chat(String query);
    public String chatTemplate(String query,String userId);

    Flux<String> streamChat(String query);
}
