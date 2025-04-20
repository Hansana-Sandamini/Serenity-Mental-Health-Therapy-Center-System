package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.PatientDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class PatientDAOImpl implements PatientDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Patient entity) throws Exception {
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
    public boolean update(Patient entity) throws Exception {
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
            Patient patient = session.find(Patient.class, id);
            if (patient != null) {
                session.remove(patient);
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
    public Patient find(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(Patient.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Patient> getAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Patient> patients = session.createQuery("FROM Patient ", Patient.class).list();
        session.close();
        return patients;
    }

    @Override
    public List<String> loadAllIDs() throws Exception {
        Session session = factoryConfiguration.getSession();
        try {
            List<Patient> patients = session.createQuery("FROM Patient ", Patient.class).list();
            return patients.stream()
                    .map(Patient::getPatientId)
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
                    "SELECT p.patientId FROM Patient p ORDER BY p.patientId DESC",
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
                    "SELECT p.patientId FROM Patient p ORDER BY p.patientId DESC",
                    String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null) {
                String numericPart = lastId.substring(3);
                int nextNum = Integer.parseInt(numericPart) + 1;
                return String.format("PT-%03d", nextNum);
            } else {
                return "PT-001";
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
