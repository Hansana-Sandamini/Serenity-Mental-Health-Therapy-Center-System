package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PaymentBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PaymentDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        List<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

//        for (Payment payment : payments) {
//            paymentDTOS.add(new PaymentDTO(
//                    payment.getPaymentId(),
//                    payment.getPatient().getPatientId(),
//                    payment.getTherapyProgram().getProgramId(),
//                    payment.getSession().getSessionId(),
//                    payment.getAmount(),
//                    payment.getAmountPaid(),
//                    payment.getAmountToPay(),
//                    payment.getDate(),
//                    payment.getTime(),
//                    payment.getStatus()
//            ));
//        }
        return paymentDTOS;
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {
        return false;
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
