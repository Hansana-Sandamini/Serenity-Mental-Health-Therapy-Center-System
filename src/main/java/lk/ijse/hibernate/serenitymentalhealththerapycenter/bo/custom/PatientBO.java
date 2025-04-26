package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;

import java.util.List;

public interface PatientBO extends SuperBO {
    public List<PatientDTO> getAllPatients() throws Exception;
    public boolean savePatient(PatientDTO patientDTO) throws Exception;
    public boolean updatePatient(PatientDTO patientDTO) throws Exception;
    public boolean deletePatient(String selectedPatient) throws Exception;
    public String getNextPatientId() throws Exception;
    public List<String> loadAllPatientIds() throws Exception;
    Patient searchPatient(String selectedPatientId) throws Exception;
}
