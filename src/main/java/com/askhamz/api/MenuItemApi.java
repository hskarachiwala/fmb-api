package com.askhamz.api;

import com.askhamz.api.model.MenuItem;
import org.glassfish.jersey.spi.Contract;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Contract
public interface MenuItemApi {
    void saveMenuItem(MenuItem menuItem);
    Optional<MenuItem> getMenuItemById(UUID menuItemId);
    List<MenuItem> getMenuItems();
}
