package com.askhamz.db.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(value = "MenuItems", noClassnameStored = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MongoMenuItem {
    @Id
    private String id;
    private String name;
    private Boolean isVeg;
    private String menuItemType;
    private List<String> ingredients;
}
