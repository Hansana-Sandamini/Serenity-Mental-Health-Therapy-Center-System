package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PatientBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.PatientDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {

    PatientDAOImpl patientDAO = (PatientDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {
        List<Patient> patients = patientDAO.getAll();
        ArrayList<PatientDTO> patientDTOS = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOS.add(new PatientDTO(
                    patient.getPatientId(),
                    patient.getName(),
                    patient.getContactNumber(),
                    patient.getEmail(),
                    patient.getAge(),
                    patient.getRegistrationDate()
            ));
        }
        return patientDTOS;
    }

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        return patientDAO.save(new Patient(
                patientDTO.getPatientId(),
                patientDTO.getName(),
                patientDTO.getContactNumber(),
                patientDTO.getEmail(),
                patientDTO.getAge(),
                patientDTO.getRegistrationDate()
        ));
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        return patientDAO.update(new Patient(
                patientDTO.getPatientId(),
                patientDTO.getName(),
                patientDTO.getContactNumber(),
                patientDTO.getEmail(),
                patientDTO.getAge(),
                patientDTO.getRegistrationDate()
        ));
    }

    @Override
    public boolean deletePatient(String selectedPatient) throws Exception {
        return patientDAO.delete(selectedPatient);
    }

    @Override
    public String getNextPatientId() throws Exception {
        return patientDAO.generateNewId();
    }

    @Override
    public List<String> loadAllPatientIds() throws Exception {
        return patientDAO.loadAllIDs();
    }

    @Override
    public Patient searchPatient(String selectedPatientId) throws Exception {
        return patientDAO.find(selectedPatientId);
    }
}
