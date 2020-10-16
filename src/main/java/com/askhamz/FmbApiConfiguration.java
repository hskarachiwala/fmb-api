package com.askhamz;

import com.askhamz.db.MongoConfig;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

//YAML config file gets deserialized into this class
@Getter
@Setter
public class FmbApiConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    private MongoConfig mongoConfig;
}
