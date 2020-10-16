package com.askhamz.db;

import java.util.List;
import java.util.Map;

public interface MenuItemDao {
    List<Map<String, Object>> getMenuItems();
    void saveMenuItem(Map<String, Object> json);
}
