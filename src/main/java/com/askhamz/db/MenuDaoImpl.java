package com.askhamz.db;

import com.askhamz.db.model.MongoMenu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

public class MenuDaoImpl implements MenuDao {

    private final Datastore dataStore;
    private final ObjectMapper objectMapper;

    @Inject
    public MenuDaoImpl(Morphia morphia, MongoClient mongoClient, @Named("DB_NAME") String dbName,
                           ObjectMapper objectMapper) {
        this.dataStore = morphia.createDatastore(mongoClient, dbName);
        this.objectMapper = objectMapper;
    }


    @Override
    public Map<String, Object> getMenuByDate(String date) {
        return null;
    }

    @Override
    public void saveMenu(Map<String, Object> json) {
        MongoMenu mongoMenu = getMenuFromJson(json);
        dataStore.save(mongoMenu);
    }

    private MongoMenu getMenuFromJson(Map<String, Object> json) {
        MongoMenu mongoMenu = MongoMenu.builder()
                .id(json.get("id").toString())
                .menuDate(json.get("menuDate").toString())
                .menuItems((List<String>)json.get("menuItems"))
                .build();
        return mongoMenu;
    }
}
