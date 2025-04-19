package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.UserDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User find(String s) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        return List.of();
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        return List.of();
    }

    @Override
    public String getLastID() throws Exception {
        return "";
    }
}
