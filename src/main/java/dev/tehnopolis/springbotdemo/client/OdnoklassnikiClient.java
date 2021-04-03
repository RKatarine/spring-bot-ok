package dev.tehnopolis.springbotdemo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class OdnoklassnikiClient {
    private static final Logger logger = LoggerFactory.getLogger(OdnoklassnikiClient.class);

    @Autowired
    RestTemplate template;

    @Autowired
    ClientConfig config;

    @PostConstruct
    void init(){
        if(!checkSubscriptions() && !subscribe()){
            logger.error("Failed to subscribe. Exiting...");
            System.exit(1);
        }
    }

    private boolean subscribe() {
        logger.info("Trying to subscribe... ");
        final String methodUrl = String.format(
                config.getMethodUrlTemplate(),
                config.getSubscribeMethod(),
                config.getToken()
        );

        final SubscribeResponse response = template.postForObject(
                methodUrl, new SubscribeBody(config.getBotHook()), SubscribeResponse.class
        );

        return response != null && response.isSuccess();
    }

    public boolean checkSubscriptions() {
        logger.info("Checking for subscriptions...");
        final SubscriptionsResponse response = template.getForObject(
                String.format(
                        config.getMethodUrlTemplate(),
                        config.getSubscriptionMethod(),
                        config.getToken()
                ),
                SubscriptionsResponse.class
        );

        if (response == null || response.getSubscriprions() == null || response.getSubscriprions().isEmpty()) {
            logger.info("Subscriptions response is empty");
            return false;
        }

        final String botHook = config.getBotHook();

        final boolean result = response.getSubscriprions().stream().anyMatch(sub -> botHook.equals(sub.getUrl()));
        if (result) {
            logger.info("There is a subscription " + botHook);
        } else {
            logger.info("There is no subscription " + botHook);
        }
        return result;
    }
}
