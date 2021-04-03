package dev.tehnopolis.springbotdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Sender {
    @JsonProperty("user_id")
    String userId;
}