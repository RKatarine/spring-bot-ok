package dev.tehnopolis.springbotdemo.client.urbanDict;

import dev.tehnopolis.springbotdemo.model.ok.Recipient;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SendedMessageBody {
    Recipient recipient;
    Message message;

    public SendedMessageBody(String chatId, String text) {
        recipient = new Recipient(chatId);
        message = new Message(text);
    }

    @Data
    @ToString
    public static class Message {
        String text;

        public Message(String text) {
            this.text = text;
        }
    }
}
