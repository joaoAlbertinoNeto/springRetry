package com.spring.retry.infrastructure.rest.out;

import com.spring.retry.application.port.out.KamikazechaserPortOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class KamikazechaserPortOutImpl implements KamikazechaserPortOut {
    private final String url ;
    private final RestTemplate restTemplate;

    public KamikazechaserPortOutImpl(
        @Value("${url.out.provider}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Retryable(retryFor = HttpClientErrorException.class, backoff = @Backoff(delay = 100))
    @Override
    public Object getData(String param) {
        log.info("Retry attempt : {} ", RetrySynchronizationManager.getContext().getRetryCount());
        try {
            String finalUrl = url + param + ".json";
            return restTemplate.getForEntity(finalUrl, Object.class);
        }catch (HttpClientErrorException err){
            log.error(err.getLocalizedMessage());
            throw err;
        }
    }
}