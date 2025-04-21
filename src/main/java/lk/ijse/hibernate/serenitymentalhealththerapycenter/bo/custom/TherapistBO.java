package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapistDTO;

import java.util.List;

public interface TherapistBO extends SuperBO {
    public List<TherapistDTO> getAllTherapists() throws Exception;
    public boolean saveTherapist(TherapistDTO therapistDTO) throws Exception;
    public boolean updateTherapist(TherapistDTO therapistDTO) throws Exception;
    public boolean deleteTherapist(String selectedTherapist) throws Exception;
    public String getNextTherapistId() throws Exception;
    public List<TherapistDTO> loadAllTherapistIds() throws Exception;
}
