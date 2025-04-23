package lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User getUserByUsername(String username) throws Exception;
}
