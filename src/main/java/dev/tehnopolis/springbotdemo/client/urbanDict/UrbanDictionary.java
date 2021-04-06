package dev.tehnopolis.springbotdemo.client.urbanDict;

import dev.tehnopolis.springbotdemo.client.ClientConfig;
import dev.tehnopolis.springbotdemo.model.urbanDict.Translation;
import dev.tehnopolis.springbotdemo.model.urbanDict.UrbanDictionaryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class UrbanDictionary {
    private static final Logger logger = LoggerFactory.getLogger(UrbanDictionary.class);

    @Autowired
    RestTemplate template;

    @Autowired
    ClientConfig config;

    public List<Translation> getTranslations(String query) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("x-rapidapi-key", config.getXRapidapiKey());
        headers.set("x-rapidapi-host", config.getXRapidapiHost());
        headers.set("useQueryString", "true");

        HttpEntity<String> entity = new HttpEntity<String>(headers);


        String methodUrl = String.format("%s?term=%s", config.getTranslateMethod(), query);

        logger.info("Try request get translation for <" + query + ">");
        logger.info("Headers " + entity.getHeaders());

        ResponseEntity<UrbanDictionaryResponse> response;

        try {
            response = template.exchange(methodUrl, HttpMethod.GET, entity, UrbanDictionaryResponse.class);
        } catch (HttpClientErrorException e) {

            logger.error("Error while get translation for <" + query + "> " + e.getMessage());
            return Collections.emptyList();
        }

        if (response.getBody().getList().isEmpty()) {
            logger.info("Not found translation for word <" + query + ">");
            return Collections.emptyList();
        }

        logger.info("Get translation " + response.getBody().getList().size());

        return response.getBody().getList();
    }
}
