package dev.tehnopolis.springbotdemo.model.urbanDict;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Translation {
    String definition;

    String permalink;

    @JsonProperty("thumbs_up")
    int thumbsUp;

    @JsonProperty("sound_urls")
    List<String> soundUrls;

    String author;

    String word;

    int defid;

    @JsonProperty("current_vote")
    String currentVote;

    @JsonProperty("written_on")
    String writtenOn;

    String example;

    @JsonProperty("thumbs_down")
    int thumbsDown;
}
