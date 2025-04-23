package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.TherapistBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Therapist;

import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAOImpl therapistDAO = (TherapistDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);

    @Override
    public List<TherapistDTO> getAllTherapists() throws Exception {
        List<Therapist> therapists = therapistDAO.getAll();
        ArrayList<TherapistDTO> therapistDTOS = new ArrayList<>();

        for (Therapist therapist : therapists) {
            therapistDTOS.add(new TherapistDTO(
                    therapist.getTherapistId(),
                    therapist.getName(),
                    therapist.getContactNumber(),
                    therapist.getEmail(),
                    therapist.getAvailabilityStatus(),
                    therapist.getSpecialization()
            ));
        }
        return therapistDTOS;
    }

    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) throws Exception {
        return therapistDAO.save(new Therapist(
                therapistDTO.getTherapistId(),
                therapistDTO.getName(),
                therapistDTO.getContactNumber(),
                therapistDTO.getEmail(),
                therapistDTO.getAvailabilityStatus(),
                therapistDTO.getSpecialization()
        ));
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) throws Exception {
        return therapistDAO.update(new Therapist(
                therapistDTO.getTherapistId(),
                therapistDTO.getName(),
                therapistDTO.getContactNumber(),
                therapistDTO.getEmail(),
                therapistDTO.getAvailabilityStatus(),
                therapistDTO.getSpecialization()
        ));
    }

    @Override
    public boolean deleteTherapist(String selectedTherapist) throws Exception {
        return therapistDAO.delete(selectedTherapist);
    }

    @Override
    public String getNextTherapistId() throws Exception {
        return therapistDAO.generateNewId();
    }

    @Override
    public List<String> loadAllTherapistIds() throws Exception {
        return therapistDAO.loadAllIDs();
    }

    @Override
    public Therapist searchTherapist(String selectedTherapistId) throws Exception {
        return therapistDAO.find(selectedTherapistId);
    }
}
