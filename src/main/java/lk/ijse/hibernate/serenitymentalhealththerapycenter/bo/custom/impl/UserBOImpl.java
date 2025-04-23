package lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.impl;

import lk.ijse.hibernate.serenitymentalhealththerapycenter.bo.custom.UserBO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dao.custom.impl.UserDAOImpl;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.dto.UserDTO;
import lk.ijse.hibernate.serenitymentalhealththerapycenter.entity.User;
import org.mindrot.jbcrypt.BCrypt;

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
        String hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        return userDAO.save(new User(
                userDTO.getUsername(),
                hashedPassword,
                userDTO.getName(),
                userDTO.getRole(),
                userDTO.getEmail(),
                userDTO.getContactNumber()
        ));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        String hashedPassword = userDTO.getPassword();
        if (!userDTO.getPassword().isEmpty() && !userDTO.getPassword().startsWith("$2a$")) {
            hashedPassword = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        }
        return userDAO.update(new User(
                userDTO.getUsername(),
                hashedPassword,
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

    @Override
    public UserDTO getUserByUsername(String username) throws Exception {
        User user = userDAO.getUserByUsername(username);
        if (user != null) {
            return new UserDTO(
                    user.getUsername(),
                    user.getPassword(),
                    user.getName(),
                    user.getRole(),
                    user.getEmail(),
                    user.getContactNumber()
            );
        }
        return null;
    }

    @Override
    public boolean verifyUser(String username, String password, String role) throws Exception {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getRole().equals(role)) {
            return BCrypt.checkpw(password, user.getPassword());
        }
        return false;
    }
}
