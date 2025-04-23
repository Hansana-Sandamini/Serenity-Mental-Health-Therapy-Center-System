package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapyProgramBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapyProgramDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    TherapyProgramDAOImpl therapyProgramDAO = (TherapyProgramDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public List<TherapyProgramDTO> getAllTherapyPrograms() throws Exception {
        List<TherapyProgram> programs = therapyProgramDAO.getAll();
        ArrayList<TherapyProgramDTO> programDTOS = new ArrayList<>();

        for (TherapyProgram program : programs) {
            programDTOS.add(new TherapyProgramDTO(
                    program.getProgramId(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee()
            ));
        }
        return programDTOS;
    }

    @Override
    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return therapyProgramDAO.save(new TherapyProgram(
                therapyProgramDTO.getProgramId(),
                therapyProgramDTO.getProgramName(),
                therapyProgramDTO.getDuration(),
                therapyProgramDTO.getFee()
        ));
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws Exception {
        return therapyProgramDAO.update(new TherapyProgram(
                therapyProgramDTO.getProgramId(),
                therapyProgramDTO.getProgramName(),
                therapyProgramDTO.getDuration(),
                therapyProgramDTO.getFee()
        ));
    }

    @Override
    public boolean deleteTherapyProgram(String selectedProgram) throws Exception {
        return therapyProgramDAO.delete(selectedProgram);
    }

    @Override
    public String getNextTherapyProgramId() throws Exception {
        return therapyProgramDAO.generateNewId();
    }

    @Override
    public List<String> loadAllTherapyProgramIds() throws Exception {
        return therapyProgramDAO.loadAllIDs();
    }

    @Override
    public TherapyProgram searchProgram(String selectedProgramId) throws Exception {
        return therapyProgramDAO.find(selectedProgramId);
    }
}
