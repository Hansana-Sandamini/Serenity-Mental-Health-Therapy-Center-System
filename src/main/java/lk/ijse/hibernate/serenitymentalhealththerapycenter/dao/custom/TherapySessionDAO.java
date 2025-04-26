package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;

import java.time.LocalDate;
import java.util.List;

public interface TherapySessionDAO extends CrudDAO<TherapySession, String> {
    public List<TherapySession> getSessionsByTherapistAndDate(String therapistId, String date) throws Exception;
}
