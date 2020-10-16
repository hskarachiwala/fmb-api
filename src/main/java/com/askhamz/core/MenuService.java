package com.askhamz.core;

import com.askhamz.api.MenuApi;
import com.askhamz.api.model.Menu;
import com.askhamz.api.model.MenuItem;
import com.askhamz.db.MenuDao;
import com.askhamz.db.MenuItemDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MenuService implements MenuApi {
    private final MenuDao menuDao;
    private final ObjectMapper objectMapper;

    @Inject
    public MenuService(MenuDao menuDao, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.menuDao = menuDao;
    }

    @Override
    public void saveMenu(Menu menu) {
        UUID menuId = UUID.randomUUID();
        Map<String, Object> json = objectMapper.convertValue(menu, Map.class);
        json.put("id", menu);
        menuDao.saveMenu(json);
    }

    @Override
    public Menu getMenuById(UUID getMenu) {
        return null;
    }

    @Override
    public Menu getMenuByDate(String date) {
        return null;
    }

}
