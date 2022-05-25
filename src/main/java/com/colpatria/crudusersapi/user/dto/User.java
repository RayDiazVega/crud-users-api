package com.colpatria.crudusersapi.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @JsonIgnore
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @JsonIgnore
  @LastModifiedDate
  @Column
  private LocalDateTime lastModifiedDate;
}
