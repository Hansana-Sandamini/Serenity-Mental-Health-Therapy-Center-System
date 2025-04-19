package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.TherapyProgramDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;

import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {

    @Override
    public boolean save(TherapyProgram entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(TherapyProgram entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public TherapyProgram find(String s) throws Exception {
        return null;
    }

    @Override
    public List<TherapyProgram> getAll() throws Exception {
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
