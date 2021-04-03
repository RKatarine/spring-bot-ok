package dev.tehnopolis.springbotdemo;

import dev.tehnopolis.springbotdemo.model.Notification;
import dev.tehnopolis.springbotdemo.model.ResponseHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/bothook")
    @ResponseBody
    public ResponseHook serveHook(@RequestBody Notification notification) {
        logger.info("Got the message " + notification);
        return new ResponseHook(true);
    }
}
