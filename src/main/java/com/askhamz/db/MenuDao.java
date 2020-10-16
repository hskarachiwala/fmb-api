package com.askhamz.db;

import java.util.Map;

public interface MenuDao {
    Map<String, Object> getMenuByDate(String date);
    void saveMenu(Map<String, Object> json);
}
