package com.askhamz.api.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @NonNull
    private UUID menuId;
    @NonNull
    private List<UUID> menuItems;
    @NonNull
    private String menuDate;
}
