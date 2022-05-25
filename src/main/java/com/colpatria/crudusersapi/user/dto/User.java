package com.colpatria.crudusersapi.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long id;

  @NotNull(message = "names is required")
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "names must be a valid value")
  @Column(nullable = false)
  private String names;

  @NotNull(message = "surnames is required")
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "surnames must be a valid value")
  @Column(nullable = false)
  private String surnames;

  @NotNull(message = "email is required")
  @Email(message = "email must be a valid value")
  @Column(nullable = false, unique = true)
  private String email;
}
