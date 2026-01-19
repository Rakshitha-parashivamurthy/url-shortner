package com.example.urlshortner.controller;

import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "redirect:/test.html";
    }

    @GetMapping("/redirect/{shortUrl}")
    public String redirectToOriginal(@PathVariable String shortUrl) {
        String original = urlService.getOriginalUrl(shortUrl);
        if (original == null || original.isEmpty()) {
            return "redirect:/";
        }
        return "redirect:" + original;
    }
}
