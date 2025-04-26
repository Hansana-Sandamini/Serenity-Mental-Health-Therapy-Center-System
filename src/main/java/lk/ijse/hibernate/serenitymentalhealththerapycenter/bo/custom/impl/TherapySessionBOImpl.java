package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.*;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapySessionDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Therapist;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAOImpl therapySessionDAO = (TherapySessionDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_SESSION);
    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);
    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);

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
        Therapist therapist = therapistBO.searchTherapist(therapySessionDTO.getTherapistId());
        Patient patient = patientBO.searchPatient(therapySessionDTO.getPatientId());
        TherapyProgram therapyProgram = therapyProgramBO.searchProgram(therapySessionDTO.getProgramId());

        if (therapist == null || patient == null || therapyProgram == null) {
            throw new Exception("Invalid therapist, patient, or therapy program ID");
        }

        TherapySession therapySession = new TherapySession();
        therapySession.setSessionId(therapySessionDTO.getSessionId());
        therapySession.setTherapist(therapist);
        therapySession.setPatient(patient);
        therapySession.setTherapyProgram(therapyProgram);
        therapySession.setDate(therapySessionDTO.getDate());
        therapySession.setTime(therapySessionDTO.getTime());
        therapySession.setStatus(therapySessionDTO.getStatus());

        boolean isSaved = therapySessionDAO.save(therapySession);

        if (isSaved) {
            updateTherapistAvailability(therapySessionDTO.getTherapistId(), therapySessionDTO.getDate());
        }

        return isSaved;
    }

    @Override
    public boolean updateTherapySession(TherapySessionDTO therapySessionDTO) throws Exception {
        Therapist therapist = therapistBO.searchTherapist(therapySessionDTO.getTherapistId());
        Patient patient = patientBO.searchPatient(therapySessionDTO.getPatientId());
        TherapyProgram therapyProgram = therapyProgramBO.searchProgram(therapySessionDTO.getProgramId());

        if (therapist == null || patient == null || therapyProgram == null) {
            throw new Exception("Invalid therapist, patient, or therapy program ID");
        }

        TherapySession therapySession = new TherapySession();
        therapySession.setSessionId(therapySessionDTO.getSessionId());
        therapySession.setTherapist(therapist);
        therapySession.setPatient(patient);
        therapySession.setTherapyProgram(therapyProgram);
        therapySession.setDate(therapySessionDTO.getDate());
        therapySession.setTime(therapySessionDTO.getTime());
        therapySession.setStatus(therapySessionDTO.getStatus());

        boolean isUpdated = therapySessionDAO.update(therapySession);

        if (isUpdated) {
            updateTherapistAvailability(therapySessionDTO.getTherapistId(), therapySessionDTO.getDate());
        }

        return isUpdated;
    }

    private void updateTherapistAvailability(String therapistId, String sessionDate) throws Exception {
        Therapist therapist = therapistBO.searchTherapist(therapistId);
        if (therapist != null) {
            List<TherapySession> sessions = therapySessionDAO.getSessionsByTherapistAndDate(therapistId, sessionDate);

            if (!sessions.isEmpty()) {
                TherapistDTO therapistDTO = new TherapistDTO(
                        therapist.getTherapistId(),
                        therapist.getName(),
                        therapist.getContactNumber(),
                        therapist.getEmail(),
                        "Not Available",
                        therapist.getSpecialization()
                );
                therapistBO.updateTherapist(therapistDTO);

            } else {
                TherapistDTO therapistDTO = new TherapistDTO(
                        therapist.getTherapistId(),
                        therapist.getName(),
                        therapist.getContactNumber(),
                        therapist.getEmail(),
                        "Available",
                        therapist.getSpecialization()
                );
                therapistBO.updateTherapist(therapistDTO);
            }
        }
    }

    private void updateTherapistAvailabilityAfterDeletion(String therapistId, String sessionDate) throws Exception {
        List<TherapySession> sessions = therapySessionDAO.getSessionsByTherapistAndDate(therapistId, sessionDate);
        Therapist therapist = therapistBO.searchTherapist(therapistId);

        if (therapist != null) {
            if (sessions.isEmpty()) {
                TherapistDTO therapistDTO = new TherapistDTO(
                        therapist.getTherapistId(),
                        therapist.getName(),
                        therapist.getContactNumber(),
                        therapist.getEmail(),
                        "Available",
                        therapist.getSpecialization()
                );
                therapistBO.updateTherapist(therapistDTO);

            } else {
                TherapistDTO therapistDTO = new TherapistDTO(
                        therapist.getTherapistId(),
                        therapist.getName(),
                        therapist.getContactNumber(),
                        therapist.getEmail(),
                        "Not Available",
                        therapist.getSpecialization()
                );
                therapistBO.updateTherapist(therapistDTO);
            }
        }
    }

    @Override
    public boolean deleteTherapySession(String selectedSession) throws Exception {
        TherapySession session = therapySessionDAO.find(selectedSession);
        boolean isDeleted = therapySessionDAO.delete(selectedSession);

        if (isDeleted && session != null) {
            updateTherapistAvailabilityAfterDeletion(session.getTherapist().getTherapistId(), session.getDate());
        }

        return isDeleted;
    }

    @Override
    public String getNextTherapySessionId() throws Exception {
        return therapySessionDAO.generateNewId();
    }

    @Override
    public List<String> loadAllTherapySessionIds() throws Exception {
        return therapySessionDAO.loadAllIDs();
    }

    @Override
    public List<TherapySession> getSessionsByTherapistAndDate(String therapistId, String date) throws Exception {
        return therapySessionDAO.getSessionsByTherapistAndDate(therapistId, date);
    }
}
