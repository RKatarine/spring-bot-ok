package dev.tehnopolis.springbotdemo;

import dev.tehnopolis.springbotdemo.client.ok.OdnoklassnikiClient;
import dev.tehnopolis.springbotdemo.client.urbanDict.UrbanDictionary;
import dev.tehnopolis.springbotdemo.model.ok.Notification;
import dev.tehnopolis.springbotdemo.model.ok.ResponseHook;
import dev.tehnopolis.springbotdemo.model.urbanDict.Translation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    OdnoklassnikiClient okClient;

    @Autowired
    UrbanDictionary urbanDictionary;

    @PostMapping("/bothook")
    @ResponseBody
    public ResponseHook serveHook(@RequestBody Notification notification) {
        logger.info("Got the message " + notification);

        if (notification.getMessage().getText() != null && notification.getRecipient().getChatId() != null) {
            logger.info("Process new message...");

            final String text = notification.getMessage().getText();

            List<Translation> list = urbanDictionary.getTranslations(text);

            String textForResponse;


            if (list.isEmpty()) {
                textForResponse = "We not fond translation...";
            } else {
                textForResponse = list.get(0).getDefinition();
            }

            boolean response = okClient.sendMessage(notification.getRecipient().getChatId(), textForResponse);

            logger.info("Result attempt sending message " + response);
        }


        return new ResponseHook(true);
    }
}
