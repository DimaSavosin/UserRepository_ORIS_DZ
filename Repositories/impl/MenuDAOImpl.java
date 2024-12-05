package ru.kpfu.servlets.Repositories.impl;

import ru.kpfu.servlets.Repositories.MenuDAO;
import ru.kpfu.servlets.models.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/ORIS";
    private static final String USER = "postgres";
    private static final String PASSWORD = "vimer_401";


    private static final String INSERT_MENU_SQL = "INSERT INTO menu (name, description, price) VALUES (?, ?, ?)";
    private static final String DELETE_MENU_SQL = "DELETE FROM menu WHERE id = ?";
    private static final String UPDATE_MENU_SQL = "UPDATE menu SET name = ?, description = ?, price = ? WHERE id = ?";
    private static final String SELECT_MENU_SQL = "SELECT id, name, description, price FROM menu";

    public void downloadingDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Menu menu) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENU_SQL)) {

            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setInt(3, menu.getPrice());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Menu menu) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU_SQL)) {

            preparedStatement.setInt(1, menu.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Menu menu) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU_SQL)) {

            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setInt(3, menu.getPrice());
            preparedStatement.setInt(4, menu.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");

                menuList.add(Menu.builder().id(id).name(name).description(description).price(price).build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return menuList;
    }
}
