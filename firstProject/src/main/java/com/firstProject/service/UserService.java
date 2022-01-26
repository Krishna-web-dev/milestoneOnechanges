package com.firstProject.service;

import com.firstProject.repository.UserRepository;
import com.firstProject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

    }

    public  List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    public  void addUser(User user)
    {
        userRepository.save(user);
    }

    public void delete(String user_id) {
        userRepository.deleteById(user_id);
    }

    public boolean checkName_Email(User user)
    {
        return (userRepository.existsById(user.getUsername()) || userRepository.existsById(user.getEmail()));
    }
    public User getUser(String id){
        return userRepository.findById(id).get();
    }
  
   public ResponseEntity updateUser(User user , String username)
   {
       try {
           User tempuser = userRepository.getUser(username);

           if(tempuser== null)
               return new ResponseEntity("user does not exist ", HttpStatus.NOT_FOUND);

           tempuser.setUsername(user.getUsername());
           tempuser.setFirstname(user.getFirstname());
          tempuser.setLastname(user.getLastname());
         tempuser.setMobile(user.getMobile());
         tempuser.setEmail(user.getEmail());
         tempuser.setAddress1(user.getAddress1());
         tempuser.setAddress2(user.getAddress2());

         // update the user in userRepository
           userRepository.save(tempuser);
           // response entity help the return type variable and HTTPS
           return  new ResponseEntity ("User updated successfully",HttpStatus.OK );


       }
       catch (DataIntegrityViolationException e) {

           return new ResponseEntity("User with this information already exists",HttpStatus.CONFLICT);
       }
       catch (Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }

}
































// u can skip this
//    public boolean notValidNumber(Users users) {
//
//            int temp = users.getMobileNumber() ;
//            if(temp == (int)temp)
//                return false;
//            else
//                return  true;
//
//
//    }
// to check for updation user exist or not
// the inside get is the repostiory function
