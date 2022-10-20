package com.revature.repository;

import com.revature.model.Pokemon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListRepository {

    public List<Pokemon> getPokemon() throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            String sql = "select * from pokemon";
            List<Pokemon> set = new ArrayList<>();

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("num_id");
                String name = rs.getString("name");
                int level = rs.getInt("level");
                String image = rs.getString("image");
                Pokemon add = new Pokemon(id, name, level, image);
                set.add(add);
            }
            return set;
        }
    }

    public boolean addPokemon(int numId, String name, int level, String image) throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            String sql = "INSERT INTO pokemon AS a (num_id, name, level, image) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, numId);
            pstmt.setString(2, name);
            pstmt.setInt(3, level);
            pstmt.setString(4, image);

            int numberOfRecordsAdded = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            return true;
        }
    }

    public List<Pokemon> getPokemonByLevel() throws SQLException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            String sql = "select * from pokemon order by level desc";
            List<Pokemon> set = new ArrayList<>();

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("num_id");
                String name = rs.getString("name");
                int level = rs.getInt("level");
                String image = rs.getString("image");
                Pokemon add = new Pokemon(id, name, level, image);
                set.add(add);
            }
            return set;
        }
    }
    public boolean deletePokemon(int numId) throws SQLException{
        try (Connection connection = ConnectionFactory.createConnection()){
            String sql = "DELETE FROM pokemon WHERE num_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, numId);

            int numberOfRecordsUpdated = pstmt.executeUpdate();
            return numberOfRecordsUpdated == 1;

        }
    }
}
