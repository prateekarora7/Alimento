package com.alimento.prototype.services.implementation.user;

import com.alimento.prototype.entities.user.User;
import com.alimento.prototype.exceptions.EmailNotFoundException;
import com.alimento.prototype.exceptions.UserAlreadyExistsException;
import com.alimento.prototype.exceptions.UsernameAlreadyExistsException;
import com.alimento.prototype.exceptions.UsernameNotFoundException;
import com.alimento.prototype.repositories.user.UserRepository;
import com.alimento.prototype.services.user.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid username. Username " + username + "does not exist"));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        User user = userRepository.getUserByEmail(email).orElseThrow(()->new EmailNotFoundException("Invalid user email. User with this email does not exist"));
        return user;

    }

    public List<User> getUsersByCreationDate(@Param("date") LocalDate date){
        return userRepository.getUsersByCreationDate(date);
    }

    @Override
    public void updateUsername(String oldUsername, String newUsername) {
        boolean usernameExists = userRepository.getUserByUsername(newUsername).isPresent();
        if(!usernameExists) userRepository.updateUsername(oldUsername, newUsername);
        else{
            throw new UsernameAlreadyExistsException(newUsername+" is not available");
        }
    }

    @Override
    public void updatePhoneNumber(String username, String newPhoneNumber) {
        //No implementation for this method as we need OTP verification for this
    }

    @Override
    public void updateName(String username, String firstName, String lastName) {
        boolean usernameExists = userRepository.getUserByUsername(username).isPresent();
        if(usernameExists) userRepository.updateName(username, firstName, lastName);
        else{
            throw new UsernameNotFoundException(username + " does not exist");
        }
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        boolean usernameExists = userRepository.getUserByUsername(username).isPresent();
        if(usernameExists) userRepository.updatePassword(username, newPassword);
        else{
            throw new UsernameNotFoundException(username + " does not exist");
        }
    }

    @Override
    public void deleteUserByUsername(String username) {
        boolean usernameExists = userRepository.getUserByUsername(username).isPresent();
        if(usernameExists) userRepository.deleteUserByUsername(username);
        else{
            throw new UsernameNotFoundException(username + " does not exist");
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        boolean emailExists = userRepository.getUserByEmail(email).isPresent();
        if(emailExists) userRepository.deleteUserByEmail(email);
        else{
            throw new EmailNotFoundException(email + " does not exist");
        }
    }

}
