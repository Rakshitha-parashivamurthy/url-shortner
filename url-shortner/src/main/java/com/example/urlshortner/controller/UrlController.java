package com.example.urlshortner.controller;

import com.example.urlshortner.model.UrlMapping;
import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public UrlMapping shortenUrl(@RequestParam String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortUrl}")
    public String getOriginalUrl(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
}
