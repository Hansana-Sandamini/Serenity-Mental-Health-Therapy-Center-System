package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapySessionDTO;

import java.util.List;

public interface TherapySessionBO extends SuperBO{
    public List<TherapySessionDTO> getAllTherapySessions() throws Exception;
    public boolean saveTherapySession(TherapySessionDTO therapySessionDTO) throws Exception;
    public boolean updateTherapySession(TherapySessionDTO therapySessionDTO) throws Exception;
    public boolean deleteTherapySession(String selectedSession) throws Exception;
    public String getNextTherapySessionId() throws Exception;
    public List<TherapySessionDTO> loadAllTherapySessionIds() throws Exception;
}
