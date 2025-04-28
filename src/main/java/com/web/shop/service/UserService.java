package com.web.shop.service;

import com.web.shop.exceptionhandling.UserAlreadyExistsException;
import com.web.shop.mapper.UserMapper;
import com.web.shop.model.api.UserRequest;
import com.web.shop.model.table.User;
import com.web.shop.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

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
    user.setIsVerified(false); // default
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
              user.setIsVerified(updatedUser.getIsVerified());
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
}
