package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.TherapistDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {

    @Override
    public boolean save(Therapist entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Therapist entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Therapist find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Therapist> getAll() throws Exception {
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
