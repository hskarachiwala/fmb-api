package com.askhamz.resources;

import com.askhamz.api.MenuApi;
import com.askhamz.api.MenuItemApi;
import com.askhamz.api.model.Menu;
import com.askhamz.api.model.MenuItem;
import com.codahale.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {
    private final MenuApi menuApi;

    @Inject
    public MenuResource(MenuApi menuApi) {
        this.menuApi = menuApi;
    }

    @GET
    @Timed
    public Response getMenuItemsByDate(@QueryParam("date") String date) {
        Menu menu = menuApi.getMenuByDate(date);
        return Response.ok().entity(menu).build();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveMenu(Menu menu) {
        menuApi.saveMenu(menu);
        return Response.accepted().build();
    }

}
