package com.askhamz.db;

import com.askhamz.db.model.MongoMenuItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class MenuItemDaoImpl implements MenuItemDao {

    private final Datastore dataStore;
    private final ObjectMapper objectMapper;

    @Inject
    public MenuItemDaoImpl(Morphia morphia, MongoClient mongoClient, @Named("DB_NAME") String dbName,
                           ObjectMapper objectMapper) {
        this.dataStore = morphia.createDatastore(mongoClient, dbName);
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Map<String, Object>> getMenuItems() {
        List<MongoMenuItem> items = dataStore.createQuery(MongoMenuItem.class)
                .find()
                .toList();
        List<Map<String, Object>> result = items
                .stream()
                .map(item -> objectMapper.convertValue(item, new TypeReference<Map<String, Object>>() {}))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void saveMenuItem(Map<String, Object> json) {
        MongoMenuItem mongoMenuItem = getMenuItemFromJson(json);
        dataStore.save(mongoMenuItem);
    }

    private MongoMenuItem getMenuItemFromJson(Map<String, Object> json) {
        MongoMenuItem mongoMenuItem = MongoMenuItem.builder()
                .id(json.get("id").toString())
                .name(json.get("name").toString())
                .menuItemType(json.get("menuItemType").toString())
                .isVeg(Boolean.getBoolean(json.get("isVeg").toString()))
                .ingredients((List<String>) json.get("ingredients"))
                .build();
        return mongoMenuItem;
    }
}
