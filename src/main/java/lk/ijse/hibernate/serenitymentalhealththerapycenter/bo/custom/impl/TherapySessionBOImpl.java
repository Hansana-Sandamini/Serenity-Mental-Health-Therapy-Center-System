package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapySessionBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAOImpl therapySessionDAO = (TherapySessionDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_SESSION);

    @Override
    public List<TherapySessionDTO> getAllTherapySessions() throws Exception {
        List<TherapySession> sessions = therapySessionDAO.getAll();
        ArrayList<TherapySessionDTO> sessionDTOS = new ArrayList<>();

        for (TherapySession session : sessions) {
            sessionDTOS.add(new TherapySessionDTO(
                    session.getSessionId(),
                    session.getPatient().getPatientId(),
                    session.getTherapyProgram().getProgramId(),
                    session.getTherapist().getTherapistId(),
                    session.getDate(),
                    session.getTime(),
                    session.getStatus()
            ));
        }
        return sessionDTOS;
    }

    @Override
    public boolean saveTherapySession(TherapySessionDTO therapySessionDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateTherapySession(TherapySessionDTO therapySessionDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTherapySession(String selectedSession) throws Exception {
        return therapySessionDAO.delete(selectedSession);
    }

    @Override
    public String getNextTherapySessionId() throws Exception {
        return therapySessionDAO.generateNewId();
    }

    @Override
    public List<String> loadAllTherapySessionIds() throws Exception {
        return therapySessionDAO.loadAllIDs();
    }
}
