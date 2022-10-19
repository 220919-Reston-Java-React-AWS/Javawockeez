package com.revature;

import com.revature.Controller.AuthController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        //Create the app
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        //Map the app to all endpoints (send the app to the controller layer)
        AuthController ac = new AuthController();
        ac.mapEndpoints(app);

        //Start the app
        app.start(8080);
    }
}
