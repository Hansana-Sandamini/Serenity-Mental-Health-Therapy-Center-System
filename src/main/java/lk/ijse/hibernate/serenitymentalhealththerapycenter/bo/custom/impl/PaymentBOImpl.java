package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PaymentBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.PatientDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PaymentDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Payment;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    PatientDAOImpl patientDAO = (PatientDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    TherapyProgramDAOImpl therapyProgramDAO = (TherapyProgramDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);
    TherapySessionDAOImpl therapySessionDAO = (TherapySessionDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_SESSION);

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        List<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(
                    payment.getPaymentId(),
                    payment.getPatient().getPatientId(),
                    payment.getTherapyProgram().getProgramId(),
                    payment.getSession().getSessionId(),
                    payment.getAmount(),
                    payment.getAmountPaid(),
                    payment.getAmountToPay(),
                    payment.getDate(),
                    payment.getTime(),
                    payment.getStatus()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        Patient patient = patientDAO.find(paymentDTO.getPatientId());
        TherapyProgram program = therapyProgramDAO.find(paymentDTO.getProgramId());
        TherapySession session = therapySessionDAO.find(paymentDTO.getSessionId());

        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                patient,
                program,
                session,
                paymentDTO.getAmount(),
                paymentDTO.getAmountPaid(),
                paymentDTO.getAmountToPay(),
                paymentDTO.getDate(),
                paymentDTO.getTime(),
                paymentDTO.getStatus()
        ));
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {
        Patient patient = patientDAO.find(paymentDTO.getPatientId());
        TherapyProgram program = therapyProgramDAO.find(paymentDTO.getProgramId());
        TherapySession session = therapySessionDAO.find(paymentDTO.getSessionId());

        return paymentDAO.update(new Payment(
                paymentDTO.getPaymentId(),
                patient,
                program,
                session,
                paymentDTO.getAmount(),
                paymentDTO.getAmountPaid(),
                paymentDTO.getAmountToPay(),
                paymentDTO.getDate(),
                paymentDTO.getTime(),
                paymentDTO.getStatus()
        ));
    }

    @Override
    public boolean deletePayment(String selectedPayment) throws Exception {
        return paymentDAO.delete(selectedPayment);
    }

    @Override
    public String getNextPaymentId() throws Exception {
        return paymentDAO.generateNewId();
    }
}
