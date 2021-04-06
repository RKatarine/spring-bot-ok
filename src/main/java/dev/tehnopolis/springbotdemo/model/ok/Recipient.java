package dev.tehnopolis.springbotdemo.model.ok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recipient {
    @JsonProperty("chat_id")
    String chatId;
}
