package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapistBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapistDTO;

import java.util.List;

public class TherapistBOImpl implements TherapistBO {

    @Override
    public List<TherapistDTO> getAllTherapists() throws Exception {
        return List.of();
    }

    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTherapist(String selectedTherapist) throws Exception {
        return false;
    }

    @Override
    public String getNextTherapistId() throws Exception {
        return "";
    }

    @Override
    public List<TherapistDTO> loadAllTherapistIds() throws Exception {
        return List.of();
    }
}
