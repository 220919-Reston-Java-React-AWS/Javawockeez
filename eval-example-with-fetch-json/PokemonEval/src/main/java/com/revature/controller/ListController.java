package com.revature.controller;

import com.revature.model.Pokemon;
import com.revature.service.ListService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class ListController {
    ListService ls = new ListService();

    public void mapEndpoints(Javalin app){
        app.get("/get-all-pokemon", (ctx) -> {

        try{
            List<Pokemon> info = ls.getPokemon();
            ctx.json(info);
            ctx.status(200);
        } catch (SQLException e){
            ctx.status(500);
        }
        });

        app.post("/add-pokemon", (ctx) -> {
            Pokemon pokemon = ctx.bodyAsClass(Pokemon.class);
            HttpSession sessioncheck = ctx.req.getSession(false);

            try{
                boolean info = ls.addPokemon(pokemon.getNumId(), pokemon.getName(), pokemon.getLevel(), pokemon.getImage());
                ctx.status(201);
                sessioncheck = ctx.req.getSession(false);
            } catch(SQLException e){
                e.printStackTrace();
                ctx.status(400);
            }
        });

        app.get("/get-all-pokemon-by-level", (ctx) -> {
            try{
                List<Pokemon> info = ls.getPokemonByLevel();
                ctx.json(info);
                ctx.status(200);
            } catch (SQLException e){
                ctx.status(500);
            }
        });
        app.delete("/delete-pokemon", (ctx) ->{
            Pokemon pokemon = ctx.bodyAsClass(Pokemon.class);
            HttpSession httpSession = ctx.req.getSession();
            Pokemon user = (Pokemon) httpSession.getAttribute("user");

            try{
                boolean remove = ls.deletePokemon(pokemon.getNumId());
                ctx.status(200);
            } catch (SQLException e){
                ctx.status(400);
            }
        });
    }
}
