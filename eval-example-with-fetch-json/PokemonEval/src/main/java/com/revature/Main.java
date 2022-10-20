package com.revature;

import com.revature.controller.ListController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.enableCorsForAllOrigins();
        });

        ListController lc = new ListController();
        lc.mapEndpoints(app);

        app.start(8080);
    }
}
