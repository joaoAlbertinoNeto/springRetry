package com.spring.retry.infrastructure.rest.out;

import com.spring.retry.application.port.out.KamikazechaserPortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class KamikazechaserPortOutImpl implements KamikazechaserPortOut {

    private final String url = "https://rawcdn.githack.com/kamikazechaser/administrative-divisions-db/master/api/";

    @Autowired
    RestTemplate restTemplate;

    @Retryable(retryFor = HttpClientErrorException.class, backoff = @Backoff(delay = 100))
    @Override
    public Object getData(String param) {
        System.out.println("Retry attempt : "+ RetrySynchronizationManager.getContext().getRetryCount());
        try {
            String finalUrl = url + param + ".json";
            return restTemplate.getForEntity(finalUrl, Object.class);
        }catch (HttpClientErrorException err){
            throw err;
        }
    }



}
