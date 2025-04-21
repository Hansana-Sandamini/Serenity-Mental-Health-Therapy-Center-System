package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapyProgramBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;

import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    @Override
    public List<TherapyProgramDTO> getAllTherapyPrograms() throws Exception {
        return List.of();
    }

    @Override
    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTherapyProgram(String selectedProgram) throws Exception {
        return false;
    }

    @Override
    public String getNextTherapyProgramId() throws Exception {
        return "";
    }

    @Override
    public List<TherapyProgramDTO> loadAllTherapyProgramIds() throws Exception {
        return List.of();
    }
}
