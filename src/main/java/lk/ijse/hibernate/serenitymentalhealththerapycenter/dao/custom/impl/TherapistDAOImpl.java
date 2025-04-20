package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.TherapistDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class TherapistDAOImpl implements TherapistDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Therapist entity) throws Exception {
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
    public boolean update(Therapist entity) throws Exception {
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
            Therapist therapist = session.find(Therapist.class, id);
            if (therapist != null) {
                session.remove(therapist);
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
    public Therapist find(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(Therapist.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Therapist> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Therapist> therapists = session.createQuery("FROM Therapist ", Therapist.class).list();
        session.close();
        return therapists;
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            List<Therapist> therapists = session.createQuery("FROM Therapist", Therapist.class).list();
            return therapists.stream()
                    .map(Therapist::getTherapistId)
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
                    "SELECT t.therapistId FROM Therapist t ORDER BY t.therapistId DESC",
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
                    "SELECT t.therapistId FROM Therapist t ORDER BY t.therapistId DESC",
                    String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                String numericPart = lastId.substring(3);
                int nextNum = Integer.parseInt(numericPart) + 1;
                return String.format("T-1%03d", nextNum);
            } else {
                return "T-1001";
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
