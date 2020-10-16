package com.askhamz.api.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private UUID menuItemId;
    @NonNull
    private String name;
    private Boolean isVeg;
    private MenuItemType menuItemType;
    private List<String> ingredients;
}
