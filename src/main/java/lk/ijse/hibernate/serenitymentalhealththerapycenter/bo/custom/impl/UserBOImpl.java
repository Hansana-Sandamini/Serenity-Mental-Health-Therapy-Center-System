package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.UserDTO;

import java.util.List;

public class UserBOImpl implements UserBO {

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        return List.of();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteUser(String selectedUser) throws Exception {
        return false;
    }
}
