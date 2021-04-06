package dev.tehnopolis.springbotdemo.model.ok;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
    String text;
    String seq;
    String mid;
}
