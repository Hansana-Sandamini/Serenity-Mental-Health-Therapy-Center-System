package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType {
        USER, THERAPY_PROGRAM, THERAPY_SESSION, THERAPIST, PATIENT, PAYMENT
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case USER: return new UserDAOImpl();
            case THERAPY_PROGRAM: return new TherapyProgramDAOImpl();
            case THERAPY_SESSION: return new TherapySessionDAOImpl();
            case THERAPIST: return new TherapistDAOImpl();
            case PATIENT: return new PatientDAOImpl();
            case PAYMENT: return new PaymentDAOImpl();
            default: return null;
        }
    }
}
