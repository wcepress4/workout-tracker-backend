package pumppro.springboot_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import pumppro.springboot_backend.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import pumppro.springboot_backend.service.UserService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // build get all user REST API
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    } //getAllUsers

    // build create user REST API
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    } //createUser

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable  long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
        return ResponseEntity.ok(user);
    }

    // build update user REST API
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User userDetails) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));

        // required fields
        updateUser.setLogin(userDetails.getLogin());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setRole(userDetails.getRole());
//        updateUser.setPassword(userDetails.getPassword());

        // optional fields
        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    // build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not exist with id: " + id));

        userRepository.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // build get user id by login REST API
    @GetMapping("id/{login}")
    public ResponseEntity<Long> getUserIdByLogin(@PathVariable String login) {
        Long userId = userService.getUserIdByLogin(login);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            throw new ResourceNotFoundException("User not found with login: " + login);
        }
    }
} //UserController
