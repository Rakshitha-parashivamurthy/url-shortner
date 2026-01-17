package com.example.urlshortner.controller;

import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RedirectController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/redirect/{shortCode}")
    public void redirect(
            @PathVariable String shortCode,
            HttpServletResponse response
    ) throws IOException {

        String originalUrl = urlService.getOriginalUrl(shortCode);

        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL not found");
        }
    }
}
