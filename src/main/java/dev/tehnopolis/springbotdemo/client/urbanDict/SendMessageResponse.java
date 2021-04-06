package dev.tehnopolis.springbotdemo.client.urbanDict;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendMessageResponse {
    boolean success;

    @JsonProperty("recipient_id")
    String recipientId;

    @JsonProperty("message_id")
    String messageId;
}
