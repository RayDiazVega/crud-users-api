package com.colpatria.crudusersapi.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Table(name = "tasks")
public class Task {

  @Schema(example = "1")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Schema(example = "Poracius Baudato")
  @NotNull(message = "name is required")
  @Pattern(regexp = "[a-zA-Z.:\\s]+", message = "name must be a valid value")
  @Column(nullable = false)
  private String name;

  @Schema(example = "Yettengia Kenicillate")
  @Column
  private String description;

  @Schema(example = "9")
  @NotNull(message = "priority is required")
  @Min(value = 1, message = "priority is positive number, min 1 is required")
  @Max(value = 10, message = "priority is positive number, max 10 is required")
  @Column(nullable = false)
  private Integer priority;

  @JsonIgnore
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @JsonIgnore
  @LastModifiedDate
  @Column
  private LocalDateTime lastModifiedDate;
}
