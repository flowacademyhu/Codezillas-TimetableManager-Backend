package hu.flowacademy.timetablemanager.service.authentication;

import hu.flowacademy.timetablemanager.model.User;
import hu.flowacademy.timetablemanager.service.dto.UserDTO;

public interface IServiceForAuth {
    User findByEmail(String email);

    UserDTO activateUser(UserDTO userDTO);

    UserDTO createNewUser(String email, long groupId);
}
