package com.colpatria.crudusersapi.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
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

  @Schema(example = "1")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Schema(example = "Poracius Baudato")
  @NotNull(message = "names is required")
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "names must be a valid value")
  @Column(nullable = false)
  private String names;

  @Schema(example = "Yettengia Kenicillate")
  @NotNull(message = "surnames is required")
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "surnames must be a valid value")
  @Column(nullable = false)
  private String surnames;

  @Schema(example = "poraciusyettengia@imgur.com")
  @NotNull(message = "email is required")
  @Email(message = "email must be a valid value")
  @Column(nullable = false, unique = true)
  private String email;

  @Valid
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private Set<Task> tasks;

  @JsonIgnore
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @JsonIgnore
  @LastModifiedDate
  @Column
  private LocalDateTime lastModifiedDate;
}
