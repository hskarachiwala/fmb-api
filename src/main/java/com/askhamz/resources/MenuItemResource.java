package com.askhamz.resources;

import com.askhamz.api.model.MenuItem;
import com.askhamz.api.MenuItemApi;
import com.codahale.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/menuItems")
@Produces(MediaType.APPLICATION_JSON)
public class MenuItemResource {

    private final MenuItemApi menuItemApi;

    @Inject
    public MenuItemResource(MenuItemApi menuItemApi) {
        this.menuItemApi = menuItemApi;
    }

    @GET
    @Timed
    public Response getMenuItems() {
        List<MenuItem> items = menuItemApi.getMenuItems();
        return Response.ok().entity(items).build();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveMenuItem(MenuItem menuItem) {
        menuItemApi.saveMenuItem(menuItem);
        return Response.accepted().build();
    }
}
