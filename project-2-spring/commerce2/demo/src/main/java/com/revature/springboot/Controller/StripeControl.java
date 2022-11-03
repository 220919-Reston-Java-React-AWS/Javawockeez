package com.revature.springboot.Controller;


import com.revature.springboot.model.StripeItem;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RestController
public class StripeControl {

    @Value("${stripe.api.key}")
    private String stripeKey;

    @PostMapping("/checkout")
    public String stripeCheckout(@RequestBody List<StripeItem> payload) throws Exception {

//       log.info(payload.toString());
//        log.info(payload.get(0).toString());
//
//        log.info(payload.get(0).getProductKey());
//        log.info((payload.get(0).getProductKey()).getClass().getSimpleName());
//
//        log.info("" + payload.get(1).getAmount());
//        log.info(String.valueOf((payload.get(1).getAmount() )));

        Stripe.apiKey = stripeKey;  //secret authorization key for stripe

        //---- making the product cart list that stripe can use/read for checkout
        List<Object> lineItems = new ArrayList<>();

        for(int i=0; i < payload.size(); i++){
            //second element is Object because the values needs are a String and number
            Map<String, Object> stripeProductItem = new HashMap<>();

            //key-value pair for product using the product's stripe product key
            stripeProductItem.put("price", payload.get(i).getProductKey());

            //key-value pair for the quantity of product to buy
            stripeProductItem.put("quantity", payload.get(i).getAmount());

            //the adjustable_quantity setting needs another map for setting its options
            Map<String,Object> adjParams = new HashMap<>();
            adjParams.put("enabled", true); //enable adjustable quantity
            adjParams.put("minimum", 0L);   // min is 1
            adjParams.put("maximum", 100L); // max is 100

            stripeProductItem.put("adjustable_quantity", adjParams);

            //add the map object representing a product for stripe into list
            lineItems.add(stripeProductItem);
        }

        //----- setting up the parameters for creating the stripe checkout
        Map<String, Object> params = new HashMap<>();

        //the website pages to redirect after the checkout action
        //after payment
        params.put(
                "success_url",
                "http://localhost:3000/checkout-success"
        );
        //cancel payment
        params.put(
                "cancel_url",
                "http://localhost:3000/checkout-cancel"
        );
        //add in the cart product items for purchase
        params.put("line_items", lineItems);

        //set the payment mode - uses a card
        params.put("mode", "payment");
        //adding in a billing address field
        params.put("billing_address_collection", "required");

        //the shipping_address_collection setting needs another map for setting its options
        Map<String,Object> shippingParams = new HashMap<>();
        String[] countries = {"US", "CA"};  //countries codes to ship to
        shippingParams.put("allowed_countries", countries); //set country codes

        //add the shipping address field
        params.put("shipping_address_collection", shippingParams);


        //make the Stripe checkout session
        Session session = Session.create(params);

        // doing a redirect response wouldn't work so returning the checkout url as a string instead
        return session.getUrl();

    }
}
