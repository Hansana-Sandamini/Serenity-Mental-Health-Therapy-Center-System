package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        USER, THERAPY_PROGRAM, THERAPY_SESSION, THERAPIST, PATIENT, PAYMENT, REPORT
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case USER: return new UserBOImpl();
            case THERAPY_PROGRAM: return new TherapyProgramBOImpl();
            case THERAPY_SESSION: return new TherapySessionBOImpl();
            case THERAPIST: return new TherapistBOImpl();
            case PATIENT: return new PatientBOImpl();
            case PAYMENT: return new PaymentBOImpl();
            case REPORT: return new ReportBOImpl();
            default: return null;
        }
    }
}
