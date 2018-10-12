package hu.flowacademy.timetablemanager.service;

import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;

public interface AuthService {
    User findByEmail(String email);
    boolean activateUser(User user, UserDTO userDTO);
    boolean createNewUser(String email, long groupId);
}
