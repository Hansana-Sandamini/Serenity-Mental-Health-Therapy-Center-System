package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {
    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(ID id);
    public T find(ID id);
    public List<T> getAll();
    List<ID> loadAllIDs();
    ID getLastID();
}
