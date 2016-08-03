package com.self.explanation.speech;

import com.self.explanation.readerbench.ReaderBenchClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan({"com.self.explanation.readerbench",
                "com.self.explanation.speech"})
@EnableAutoConfiguration
public class SpeechSelfExplanation {

    private static final String READER_BENCH_ENDPOINT = "http://readerbench.com:8080/selfExplanation";

    @Bean
    RestTemplate getRestTemplate(){ return new RestTemplate();}

    @Bean
    ReaderBenchClient selfExplanation(RestTemplate restTemplate){
        return new ReaderBenchClient(restTemplate, READER_BENCH_ENDPOINT);
    }

    @Bean
    BaselineTextCorpus baselineTextCorpus(){
        return new BaselineTextCorpus();
    }
}
