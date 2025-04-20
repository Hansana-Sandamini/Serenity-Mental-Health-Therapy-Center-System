package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {
    public boolean save(T entity) throws Exception;
    public boolean update(T entity) throws Exception;
    public boolean delete(ID id) throws Exception;
    public T find(ID id) throws Exception;
    public List<T> getAll() throws Exception;
    List<ID> loadAllIDs() throws Exception;
    ID getLastID() throws Exception;
    public String generateNewId() throws Exception;
}
