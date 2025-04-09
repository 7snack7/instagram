package ru.snack.spring.springboot.instagram.facade;

import org.springframework.stereotype.Component;
import ru.snack.spring.springboot.instagram.dto.UserDTO;
import ru.snack.spring.springboot.instagram.entity.User;

@Component
public class UserFacade {

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}
