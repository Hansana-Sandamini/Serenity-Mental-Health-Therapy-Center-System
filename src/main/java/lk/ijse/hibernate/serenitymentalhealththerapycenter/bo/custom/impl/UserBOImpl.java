package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.UserDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(new UserDTO(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getRole(),
                    user.getEmail(),
                    user.getContactNumber()
            ));
        }
        return userDTOS;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        return userDAO.save(new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getRole(),
                userDTO.getEmail(),
                userDTO.getContactNumber()
        ));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userDAO.update(new User(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getRole(),
                userDTO.getEmail(),
                userDTO.getContactNumber()
        ));
    }

    @Override
    public boolean deleteUser(String selectedUser) throws Exception {
        return userDAO.delete(selectedUser);
    }
}
