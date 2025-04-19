package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.PatientDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Patient entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Patient find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Patient> getAll() throws Exception {
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
