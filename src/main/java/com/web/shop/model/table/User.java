package com.web.shop.model.table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long userId;

  String email;
  String phoneNumber;
  String firstName;
  String lastName;
  Boolean isVerified;
}
