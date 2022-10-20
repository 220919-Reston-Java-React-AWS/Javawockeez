package com.revature.service;

import com.revature.model.Pokemon;
import com.revature.repository.ListRepository;

import java.sql.SQLException;
import java.util.List;

public class ListService {

    ListRepository listRepository = new ListRepository();

    public List<Pokemon> getPokemon() throws SQLException{
        List<Pokemon> info = listRepository.getPokemon();
        return info;
    }
    public boolean addPokemon(int numId, String name, int level, String image) throws SQLException{
        boolean add = listRepository.addPokemon(numId, name, level, image);
        return add;
    }
    public List<Pokemon> getPokemonByLevel() throws SQLException{
        List<Pokemon> info = listRepository.getPokemonByLevel();
        return info;
    }
    public boolean deletePokemon(int numId) throws SQLException{
        boolean success = listRepository.deletePokemon(numId);
        if (success == false) {
            return false;
        }
        return true;
    }
}
