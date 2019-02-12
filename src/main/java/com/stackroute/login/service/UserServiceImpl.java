package com.stackroute.login.service;

import com.stackroute.login.domain.UserCredentials;
import com.stackroute.login.exception.UserNotFoundException;
import com.stackroute.login.exception.WrongPasswordException;
import com.stackroute.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserServiceInterface {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Override
    public UserCredentials checkUserName(UserCredentials user) throws UserNotFoundException,WrongPasswordException {

            Optional<UserCredentials> savedUser=userRepository.findById(user.getUserName());
            if(!userRepository.existsById(user.getUserName())){
                throw new UserNotFoundException("User does not exists");
            }
            if(!(user.getPassword().equals(savedUser.get().getPassword()))){
                throw new WrongPasswordException("Wrong Password");
            }
            return user;
    }

}
