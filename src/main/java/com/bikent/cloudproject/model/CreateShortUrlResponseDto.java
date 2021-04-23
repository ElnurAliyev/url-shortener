package com.bikent.cloudproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateShortUrlResponseDto {
    private String originalUrl;
    private String shortUrl;
}
