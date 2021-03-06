package com.cursor.dao.impls;

import com.cursor.dao.interfaces.CRUD;
import com.cursor.model.User;
import com.cursor.service.exceptions.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDb implements CRUD<User> {

    private static final String userName = "roman";
    private static final String userPassword = "root";
    private static final String url = "jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC";
    private static UserDaoDb instance;

    private UserDaoDb() {
    }

    public static UserDaoDb getInstance() {
        if (instance == null) {
            instance = new UserDaoDb();
        }
        return instance;
    }

    @Override
    public boolean create(User entity) {
        try (Connection connection = DriverManager.getConnection(url, userName, userPassword)) {
            Statement statement = connection.createStatement();
            String input = "insert into users(name, password) values ('" + entity.getUsername() + "', '" + entity.getPassword() + "')";
            statement.executeUpdate(input);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> usersDb = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, userName, userPassword)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            String input = "select id, name, password from users";
            resultSet = statement.executeQuery(input);
            while (resultSet.next()) {
                int uId = resultSet.getInt(1);
                String uName = resultSet.getString(2);
                String uPassword = resultSet.getString(3);
                User user = new User(uName, uPassword);
                user.setId(uId);
                usersDb.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersDb;
    }

    @Override
    public User findById(int id) {
        ArrayList<User> usersDb = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, userName, userPassword)) {
            Statement statement = connection.createStatement();
            String input = "select * from users where id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(input);
            while (resultSet.next()) {
                int uId = resultSet.getInt(1);
                String uName = resultSet.getString(2);
                String uPassword = resultSet.getString(3);
                User user = new User(uName, uPassword);
                user.setId(uId);
                usersDb.add(0, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!usersDb.isEmpty()) {
            return usersDb.get(0);
        } else {
            throw new NotFoundException("User doesn't found");
        }
    }

    @Override
    public boolean edit(int id, User entity) {
        try (Connection connection = DriverManager.getConnection(url, userName, userPassword)) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String input = "update users set name = '" + entity.getUsername() + "', password = '" + entity.getPassword() + "' where id = '" + id + "'";
            statement.executeUpdate(input);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, userName, userPassword)) {
            Statement statement = connection.createStatement();
            String input = "delete from users where id = '" + id + "'";
            statement.executeUpdate(input);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
