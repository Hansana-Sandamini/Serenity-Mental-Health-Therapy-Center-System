package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    public List<UserDTO> getAllUsers() throws Exception;
    public boolean saveUser(UserDTO userDTO) throws Exception;
    public boolean updateUser(UserDTO userDTO) throws Exception;
    public boolean deleteUser(String selectedUser) throws Exception;
}
