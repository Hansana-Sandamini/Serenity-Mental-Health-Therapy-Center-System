package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.PaymentDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentDAOImpl implements PaymentDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Payment entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Payment payment = session.find(Payment.class, id);
            if (payment != null) {
                session.remove(payment);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Payment find(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(Payment.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Payment> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Payment> payments = session.createQuery("FROM Payment ", Payment.class).list();
        session.close();
        return payments;
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            List<Payment> payments = session.createQuery("FROM Payment ", Payment.class).list();
            return payments.stream()
                    .map(Payment::getPaymentId)
                    .collect(Collectors.toList());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getLastID() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC",
                    String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String generateNewId() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery(
                    "SELECT p.paymentId FROM Payment p ORDER BY p.paymentId DESC",
                    String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                String numericPart = lastId.substring(3);
                int nextNum = Integer.parseInt(numericPart) + 1;
                return String.format("PAY1%03d", nextNum);
            } else {
                return "PAY1001";
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
