package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Therapist;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;

import java.util.List;

public interface TherapyProgramBO extends SuperBO {
    public List<TherapyProgramDTO> getAllTherapyPrograms() throws Exception;
    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception;
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception;
    public boolean deleteTherapyProgram(String selectedProgram) throws Exception;
    public String getNextTherapyProgramId() throws Exception;
    public List<String> loadAllTherapyProgramIds() throws Exception;
    TherapyProgram searchProgram(String selectedProgramId) throws Exception;
}
