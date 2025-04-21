package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapySessionBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapySessionDTO;

import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    @Override
    public List<TherapySessionDTO> getAllTherapySessions() throws Exception {
        return List.of();
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
        return false;
    }

    @Override
    public String getNextTherapySessionId() throws Exception {
        return "";
    }

    @Override
    public List<TherapySessionDTO> loadAllTherapySessionIds() throws Exception {
        return List.of();
    }
}
