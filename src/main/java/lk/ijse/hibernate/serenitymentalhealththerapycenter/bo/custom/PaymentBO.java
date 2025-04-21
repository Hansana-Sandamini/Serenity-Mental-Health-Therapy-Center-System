package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    public List<PaymentDTO> getAllPayments() throws Exception;
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception;
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception;
    public boolean deletePayment(String selectedPayment) throws Exception;
    public String getNextPaymentId() throws Exception;
}
