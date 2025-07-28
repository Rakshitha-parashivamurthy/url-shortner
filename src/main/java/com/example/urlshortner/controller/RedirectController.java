package com.example.urlshortner.controller;

import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/redirect/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String shortUrl = "short.ly/" + shortCode;
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        
        if (originalUrl != null && !originalUrl.isEmpty()) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Short URL not found");
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok(
            "<html><body>" +
            "<h1>🔗 URL Shortener Service</h1>" +
            "<p>Welcome to the URL Shortener API!</p>" +
            "<p><strong>API Endpoints:</strong></p>" +
            "<ul>" +
            "<li>POST /api/url/shorten?originalUrl={url} - Shorten a URL</li>" +
            "<li>GET /api/url/{shortUrl} - Get original URL (API)</li>" +
            "<li>GET /redirect/{shortCode} - Redirect to original URL (Browser)</li>" +
            "</ul>" +
            "<p><strong>Test the service:</strong> <a href='/test.html'>Open Test Page</a></p>" +
            "</body></html>"
        );
    }
}
