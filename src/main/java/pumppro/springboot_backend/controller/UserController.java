package pumppro.springboot_backend.controller;

import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    } //getAllUsers

    //build create user REST API
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    } //createUser
} //UserController
