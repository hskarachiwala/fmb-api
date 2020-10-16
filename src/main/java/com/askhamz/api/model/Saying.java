package com.askhamz.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Saying {
    private long id;
    private String content;

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
