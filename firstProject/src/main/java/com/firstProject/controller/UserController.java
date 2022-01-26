package com.firstProject.controller;

import com.firstProject.service.UserService;
import com.firstProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class UserController {


    // use UserService here
    //below private variable is used from userService so have to be injected usign autowired

    @Autowired
    private UserService userService;
//make above private
    @RequestMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @RequestMapping("/user/{userid}")
    public User getUser(@PathVariable String userid){

        return userService.getUser(userid);
    }

    @PostMapping("/user")
    public String addUsers(@RequestBody User users) {

            if (userService.checkName_Email(users))
                return " user already exist";
            else {
                userService.addUser(users);
                return "created successfully";
            }
    }

// make proper naming
    @DeleteMapping("/user/{userid}")
    public String deleteUser(@PathVariable String userid) {
        if (userid != null) {

            userService.delete(userid);
            return "user deleted";
        } else {
            return " no such user exist";
        }
    }

    @PutMapping("/users/{username}") // here two variables passed to check the username and updation
    public ResponseEntity updateUser(@RequestBody User users, @PathVariable String username){
   return  userService.updateUser(users,username);

  }


























//    @PutMapping("/users")
//    public String updateUser(@RequestBody Users users)
//    {
//        userService.
//    }

    // try and catch

//
//    {
//        try {
//            Users tempUser = userService.get(id);
//            userService.addUsers(users);
//
//        }
//        // e is used for printing the type of exception
//        catch (NoSuchElementException e) {
//            return "user doesnt exist";
//        }
//
//        return id + " user updated";
//
//
//    }
}