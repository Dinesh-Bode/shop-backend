package com.web.shop.service;

import com.web.shop.exceptionhandling.UserAlreadyExistsException;
import com.web.shop.mapper.UserMapper;
import com.web.shop.model.api.UserRequest;
import com.web.shop.model.table.User;
import com.web.shop.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;
  // private final PasswordEncoder passwordEncoder; // you should have PasswordEncoder bean
  // configured

  public User createUser(UserRequest userRequest) throws Exception {
    if (isUserExists(userRequest)) {
      throw new UserAlreadyExistsException(
          "User with phone number - "
              + userRequest.getPhoneNumber()
              + " or email- "
              + userRequest.getEmail()
              + " already present");
    }
    User user = UserMapper.userRequestUserMapper(userRequest);
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User updateUser(Long id, User updatedUser) {
    return userRepository
        .findById(id)
        .map(
            user -> {
              user.setFirstName(updatedUser.getFirstName());
              user.setLastName(updatedUser.getLastName());
              user.setEmail(updatedUser.getEmail());
              user.setPhoneNumber(updatedUser.getPhoneNumber());
              return userRepository.save(user);
            })
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  private Boolean isUserExists(UserRequest userRequest) {
    Optional<List<User>> userOptional =
        userRepository.findByEmailOrPhoneNumber(
            userRequest.getEmail(), userRequest.getPhoneNumber());
    return userOptional.isPresent() && !userOptional.get().isEmpty();
  }

  public User login(String email, String password) throws Exception {
    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new Exception("User not found"));

    if (!Objects.equals(password, user.getPassword())) {
      throw new Exception("Invalid password");
    }

    return user;
  }
}
