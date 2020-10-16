package com.askhamz.core;

import com.askhamz.api.model.MenuItem;
import com.askhamz.api.MenuItemApi;
import com.askhamz.api.model.MenuItemType;
import com.askhamz.db.MenuItemDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MenuItemService implements MenuItemApi {

    private final MenuItemDao menuItemDao;
    private final ObjectMapper objectMapper;

    @Inject
    public MenuItemService(MenuItemDao menuItemDao, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.menuItemDao = menuItemDao;
    }

    @Override
    public void saveMenuItem(MenuItem menuItem) {
        UUID menuItemId = UUID.randomUUID();
        Map<String, Object> json = objectMapper.convertValue(menuItem, Map.class);
        json.put("id", menuItemId);
        menuItemDao.saveMenuItem(json);
    }

    @Override
    public Optional<MenuItem> getMenuItemById(UUID menuItemId) {
        return Optional.empty();
    }

    @Override
    public List<MenuItem> getMenuItems() {
        List<Map<String, Object>> results = menuItemDao.getMenuItems();
        List<MenuItem> items = results.stream().map(result -> getMenuItemFromDbRecord(result)).collect(Collectors.toList());
        return items;
    }

    private MenuItem getMenuItemFromDbRecord(Map<String, Object> json) {
        return MenuItem.builder()
                .menuItemId(UUID.fromString(json.get("id").toString()))
                .name(json.get("name").toString())
                .isVeg(Boolean.getBoolean(json.get("isVeg").toString()))
                .menuItemType(MenuItemType.valueOf(json.get("menuItemType").toString()))
                .build();
    }
}
