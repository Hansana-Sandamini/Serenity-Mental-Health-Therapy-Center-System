package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.TherapyProgramDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(TherapyProgram entity) throws Exception {
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
    public boolean update(TherapyProgram entity) throws Exception {
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
            TherapyProgram therapyProgram = session.find(TherapyProgram.class, id);
            if (therapyProgram != null) {
                session.remove(therapyProgram);
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
    public TherapyProgram find(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(TherapyProgram.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<TherapyProgram> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<TherapyProgram> programs = session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
        session.close();
        return programs;
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            List<TherapyProgram> programs = session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
            return programs.stream()
                    .map(TherapyProgram::getProgramId)
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
                    "SELECT tp.programId FROM TherapyProgram tp ORDER BY tp.programId DESC",
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
                    "SELECT tp.programId FROM TherapyProgram tp ORDER BY tp.programId DESC",
                    String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                String numericPart = lastId.substring(3);
                int nextNum = Integer.parseInt(numericPart) + 1;
                return String.format("MT1%03d", nextNum);
            } else {
                return "MT1001";
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
