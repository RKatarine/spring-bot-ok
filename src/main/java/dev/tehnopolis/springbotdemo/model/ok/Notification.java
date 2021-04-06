package dev.tehnopolis.springbotdemo.model.ok;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Notification {
    String webhookType;
    Sender sender;
    Recipient recipient;
    Message message;
    long timestamp;
}
