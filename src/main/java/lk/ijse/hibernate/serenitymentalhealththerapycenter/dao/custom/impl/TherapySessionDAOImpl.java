package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.TherapySessionDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;

import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {

    @Override
    public boolean save(TherapySession entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(TherapySession entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public TherapySession find(String s) throws Exception {
        return null;
    }

    @Override
    public List<TherapySession> getAll() throws Exception {
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
