package com.askhamz.api;

import com.askhamz.api.model.Menu;

import java.util.UUID;

public interface MenuApi {
    void saveMenu(Menu menu);
    Menu getMenuById(UUID getMenu);
    Menu getMenuByDate(String date);
}
