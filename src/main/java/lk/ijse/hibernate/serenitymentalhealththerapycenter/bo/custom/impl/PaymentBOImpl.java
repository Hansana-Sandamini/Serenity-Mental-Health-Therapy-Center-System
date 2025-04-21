package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.PaymentBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PaymentDTO;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        return List.of();
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
        return false;
    }

    @Override
    public String getNextPaymentId() throws Exception {
        return "";
    }
}
