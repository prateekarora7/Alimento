package com.alimento.prototype.services.implementation.user;

import com.alimento.prototype.entities.user.User;
import com.alimento.prototype.exceptions.EmailNotFoundException;
import com.alimento.prototype.exceptions.UserAlreadyExistsException;
import com.alimento.prototype.exceptions.UsernameAlreadyExistsException;
import com.alimento.prototype.exceptions.UsernameNotFoundException;
import com.alimento.prototype.repositories.user.UserRepository;
import com.alimento.prototype.services.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) throws RuntimeException{  // This method is used to save or register new user

        boolean emailExists = userRepository.getUserByEmail(user.getEmail()).isPresent();  //validating using email

        boolean usernameExists = userRepository.getUserByUsername(user.getUsername()).isPresent();  //validating using username

        if(emailExists) throw new UserAlreadyExistsException("User with this email already exists");

        if(usernameExists) throw new UsernameAlreadyExistsException("User with this username already exists");

        //saving user if no runtime exception is thrown
        userRepository.saveUser(
                user.getEmail(), user.getPassword(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getPhoneNo(), LocalDateTime.now()
        );
    }

    @Override
    public User getUserByEmail(String email) {

        User user = userRepository.getUserByEmail(email).orElseThrow(()->new EmailNotFoundException("Invalid user email. User with this email does not exist"));
        return user;
        
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid username. User with this username does not exist"));
        return user;
    }


}
