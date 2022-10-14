package com.revature;

import com.revature.control.AuthenticationControl;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        //Create the app
        Javalin app = Javalin.create();


        //Map the app to all endpoints (send the app to the controller layer to tell Postman what to do in any event)

        AuthenticationControl ac = new AuthenticationControl();
        ac.mapEndpoints(app);

        //Start the app
        app.start(3125);
    }
}
