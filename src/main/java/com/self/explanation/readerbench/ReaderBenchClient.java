package com.self.explanation.readerbench;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class ReaderBenchClient {

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private String endpoint;

    public ReaderBenchClient() {
    }

    public ReaderBenchClient(RestTemplate restTemplate, String endpoint){
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;

        this.objectMapper = new ObjectMapper();
    }

    public SelfExplanationResult selfExplanation(SelfExplanationPayload payload) {
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<String> request = new HttpEntity<String>(
                    objectMapper.writeValueAsString(payload),
                    requestHeaders);

            try {
                String response = restTemplate.postForObject(endpoint, request, String.class);
                return objectMapper.readValue(response, SelfExplanationResult.class);
            } catch (HttpClientErrorException exc) {
                throw new RuntimeException("Could not obtain result for /selfExplanation", exc);
            } catch (IOException e) {
                throw new RuntimeException("Could not parse response", e);
            }
        } catch (Exception exc){
            throw new RuntimeException("Could not connect to Reader Bench endoint", exc);
        }
    }
}
