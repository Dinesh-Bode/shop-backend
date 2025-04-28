package com.web.shop.repository;

import com.web.shop.model.table.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByPhoneNumber(String phoneNumber);

  Optional<List<User>> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
