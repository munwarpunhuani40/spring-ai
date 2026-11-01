package com.spring.ai.firstproject.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTransformerImpl implements DataTransformer{

    @Override
    public List<Document> transform(List<Document> documentList) {
        var splitter = TokenTextSplitter
                .builder()
                .withChunkSize(350)
                .withMinChunkSizeChars(400)
                .build();
        return splitter.transform(documentList);
    }
}
