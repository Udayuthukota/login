package com.stackroute.login.controller;
import com.stackroute.login.domain.UserCredentials;
import com.stackroute.login.exception.UserNotFoundException;
import com.stackroute.login.exception.WrongPasswordException;
import com.stackroute.login.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/v1")
public class UserController {

    UserServiceInterface userServiceInterface;

@Autowired
    public UserController(UserServiceInterface userService) {
        this.userServiceInterface = userService;
    }
    @GetMapping("login")
    public ResponseEntity<String> checkUserNames(@RequestParam UserCredentials user) throws UserNotFoundException, WrongPasswordException {

    ResponseEntity<String> responseEntity;
    try {

        userServiceInterface.checkUserName(user);
        responseEntity=new ResponseEntity<>("bravo", HttpStatus.OK);
    }
    catch (Exception e){
        responseEntity=new ResponseEntity<>("loser", HttpStatus.BAD_GATEWAY);
     e.printStackTrace();
    }
    return responseEntity;
    }

}
