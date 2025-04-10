package ru.snack.spring.springboot.instagram.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.snack.spring.springboot.instagram.dto.UserDTO;
import ru.snack.spring.springboot.instagram.entity.User;
import ru.snack.spring.springboot.instagram.entity.enums.ERole;
import ru.snack.spring.springboot.instagram.exceptions.UserExistException;
import ru.snack.spring.springboot.instagram.payload.response.SignupRequest;
import ru.snack.spring.springboot.instagram.repository.UserRepository;

import java.security.Principal;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstName());
        user.setLastname(userIn.getLastName());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);
        try {
            LOG.info("Create user: {}", user);
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error creating user: {}", user, e);
            throw new UserExistException("The user " + user.getUsername()+ " already exists. Please check credentials.");
        }
    }

    public User updateUser(UserDTO userDTO, Principal principal) {
        User user =getUserByPrincipal(principal);
        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setBio(userDTO.getBio());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("The user " + id + " does not exist."));
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username));
    }
}