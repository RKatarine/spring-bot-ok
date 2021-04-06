package dev.tehnopolis.springbotdemo.model.urbanDict;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class UrbanDictionaryResponse {
    List<Translation> list;
}
