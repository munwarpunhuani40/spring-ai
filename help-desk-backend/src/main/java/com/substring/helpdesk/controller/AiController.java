package com.substring.helpdesk.controller;

import com.substring.helpdesk.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.http.converter.autoconfigure.ServerHttpMessageConvertersCustomizer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/helpdesk")
@RequiredArgsConstructor
public class AiController {

    public final AiService aiService;

    @PostMapping
    public ResponseEntity<String> getResponse(
            @RequestBody String query,
            @RequestHeader("conversationId") String conversationId
            ){
        return ResponseEntity.ok(aiService.getResponseFromAssistant(query,conversationId));

    }

    @PostMapping(value = "/stream")
    public Flux<String> streamResponse(
            @RequestBody String query,
            @RequestHeader("conversationId") String conversationId
    ){
        return this.aiService.streamResponseFromAssistant(query,conversationId);

    }


}
