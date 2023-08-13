package com.ates.userservice.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "keycloak_id")
  private String keycloakId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;
}
