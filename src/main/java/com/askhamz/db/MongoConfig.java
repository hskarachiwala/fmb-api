package com.askhamz.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoConfig {
    private String server;
    private Integer port;
    private String dbName;
}
