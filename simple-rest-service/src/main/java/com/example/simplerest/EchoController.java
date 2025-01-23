package com.example.simplerest;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/echo")
public class EchoController {

    @GetMapping
    public Map<String, String> echoGet(@RequestParam("message") String message) {
        return createResponse(message);
    }

    @PostMapping
    public Map<String, String> echoPost(@RequestBody Map<String, String> requestBody) {
        String message = requestBody.getOrDefault("message", "No message provided");
        return createResponse(message);
    }

    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}