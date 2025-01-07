package pumppro.springboot_backend.service;

import pumppro.springboot_backend.dtos.SignUpDto;
import pumppro.springboot_backend.dtos.UserDto;
import pumppro.springboot_backend.model.User;
import pumppro.springboot_backend.dtos.CredentialsDto;
import pumppro.springboot_backend.enums.Role;
import pumppro.springboot_backend.exception.AppException;
import pumppro.springboot_backend.mapper.UserMapper;
import pumppro.springboot_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
//        user.setEmail(userDto.getEmail());
//        user.setUsername(userDto.getLogin());
        user.setLogin(userDto.getLogin());
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("User not found with login: " + login, HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    public Long getUserIdByLogin(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        return userOptional.map(User::getId).orElse(null);
    }
}
