package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.TherapySessionDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class TherapySessionDAOImpl implements TherapySessionDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapySession entity) throws Exception {
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
    public boolean update(TherapySession entity) throws Exception {
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
            TherapySession therapySession = session.find(TherapySession.class, id);
            if (therapySession != null) {
                session.remove(therapySession);
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
    public TherapySession find(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(TherapySession.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<TherapySession> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<TherapySession> sessions = session.createQuery("FROM TherapySession ", TherapySession.class).list();
        session.close();
        return sessions;
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            List<TherapySession> sessions = session.createQuery("FROM TherapySession ", TherapySession.class).list();
            return sessions.stream()
                    .map(TherapySession::getSessionId)
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
                "SELECT ts.sessionId FROM TherapySession ts ORDER BY ts.sessionId DESC",
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
                    "SELECT ts.sessionId FROM TherapySession ts ORDER BY ts.sessionId DESC",
                    String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                String numericPart = lastId.substring(3);
                int nextNum = Integer.parseInt(numericPart) + 1;
                return String.format("TS1%03d", nextNum);
            } else {
                return "TS1001";
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<TherapySession> getSessionsByTherapistAndDate(String therapistId, String date) throws Exception {
        Session session = factoryConfiguration.getSession();
        String hql = "FROM TherapySession WHERE therapist.therapistId = :therapistId AND date = :date";
        Query<TherapySession> query = session.createQuery(hql, TherapySession.class);
        query.setParameter("therapistId", therapistId);
        query.setParameter("date", date);
        return query.list();
    }
}
