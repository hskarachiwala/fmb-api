package com.askhamz.db.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.*;

import java.util.List;

@Entity(value = "Menu", noClassnameStored = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MongoMenu {
    @Id
    private String id;
    private List<String> menuItems;
    private String menuDate;
}
