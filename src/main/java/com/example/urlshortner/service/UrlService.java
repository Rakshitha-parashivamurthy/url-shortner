package com.example.urlshortner.service;

import com.example.urlshortner.model.UrlMapping;
import com.example.urlshortner.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public UrlMapping shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        UrlMapping mapping = new UrlMapping(originalUrl, shortUrl);
        return urlRepository.save(mapping);
    }

    public String getOriginalUrl(String shortUrl) {
        UrlMapping mapping = urlRepository.findByShortUrl(shortUrl);
        return (mapping != null) ? mapping.getOriginalUrl() : null;
    }

    private String generateShortUrl() {
        Random random = new Random();
        int randomNum = 100000 + random.nextInt(900000);
        return String.valueOf(randomNum);
    }
}
