package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.PaymentDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Payment find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Payment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        return List.of();
    }

    @Override
    public String getLastID() throws Exception {
        return "";
    }
}
