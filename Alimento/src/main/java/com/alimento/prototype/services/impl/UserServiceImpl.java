package com.alimento.prototype.services.impl;

import com.alimento.prototype.entities.Comment;
import com.alimento.prototype.entities.User;
import com.alimento.prototype.exceptions.UserAlreadyExistsException;
import com.alimento.prototype.exceptions.UserIdNotFoundException;
import com.alimento.prototype.repositories.UserRepository;
import com.alimento.prototype.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is used to save or register new user
    @Override
    public void saveUser(User user) throws RuntimeException{

        //getting user id from the user object for validation if user already exists or not
        String email = user.getEmail();

        //validating using email
        boolean userExists = userRepository.getUserByEmail(email).isPresent();

        if(userExists) throw new UserAlreadyExistsException("User with this email already exists");

        //saving user if no runtime exception is thrown
        userRepository.saveUser(
                user.getEmail(), user.getPassword(), user.getUserId(), user.getFirstName(), user.getLastName(), user.getPhoneNo(), LocalDateTime.now()
        );
    }

    @Override
    public User getUserByEmail(String email) {

        User user = userRepository.getUserByEmail(email).orElseThrow(()->new UserIdNotFoundException("Invalid user email. User with this email does not exist"));
        return user;
        
    }


}
