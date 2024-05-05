package com.codemy.backdev.controller;

import com.codemy.backdev.exception.UserNotFoundException;
import com.codemy.backdev.model.User;
import com.codemy.backdev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User userRegister(@RequestBody User userRegister){
        return userRepository.save(userRegister);
    }

    @GetMapping("/users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id).map(user->{
            user.setUserName(newUser.getUserName());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPhoneNumber(newUser.getPhoneNumber());
            return userRepository.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
         if(!userRepository.existsById(id)){
             throw new UserNotFoundException(id);
         }
         userRepository.deleteById(id);
         return "User with id "+id+" has been deleted success.";
    }
}
