package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PatientBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PatientDTO;

import java.util.List;

public class PatientBOImpl implements PatientBO {

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {
        return List.of();
    }

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deletePatient(String selectedPatient) throws Exception {
        return false;
    }

    @Override
    public String getNextPatientId() throws Exception {
        return "";
    }

    @Override
    public List<PatientDTO> loadAllPatientIds() throws Exception {
        return List.of();
    }
}
