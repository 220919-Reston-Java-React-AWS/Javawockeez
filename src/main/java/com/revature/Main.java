package com.revature;

import com.revature.control.AuthenticationControl;
import com.revature.control.CartControl;
import com.revature.control.ProductSearchControl;
import com.revature.control.RegistrationControl;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        //Create the app
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });

        //Map the app to all endpoints (send the app to the controller layer to tell Postman what to do in any event)

        AuthenticationControl ac = new AuthenticationControl();
        ac.mapEndpoints(app);

        RegistrationControl rc = new RegistrationControl();
        rc.mapEndpoints(app);

        ProductSearchControl psc = new ProductSearchControl();
        psc.mapEndpoints(app);

        CartControl cc = new CartControl();
        cc.mapEndpoints(app);

        //Start the app
        app.start(3125);
    }
}
