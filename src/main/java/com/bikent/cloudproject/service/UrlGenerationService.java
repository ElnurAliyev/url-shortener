package com.bikent.cloudproject.service;

import com.bikent.cloudproject.dao.UrlRepository;
import com.bikent.cloudproject.model.CreateShortUrlRequestDto;
import com.bikent.cloudproject.model.CreateShortUrlResponseDto;
import com.bikent.cloudproject.utils.Base62Generator;

public class UrlGenerationService {
    private final UrlRepository repository;
    public UrlGenerationService() {
        repository = new UrlRepository();
    }
    
    public CreateShortUrlResponseDto createShortUrl(CreateShortUrlRequestDto requestDto) {
        String generatedShortUrl = Base62Generator.nextBase62Code(7);
        try {
            repository.saveUrl(generatedShortUrl, requestDto.getUrl());
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return CreateShortUrlResponseDto.builder()
                .originalUrl(requestDto.getUrl())
                .shortUrl(generatedShortUrl).build();
    }
}
