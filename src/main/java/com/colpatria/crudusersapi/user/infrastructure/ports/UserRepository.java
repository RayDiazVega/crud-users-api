package com.colpatria.crudusersapi.user.infrastructure.ports;

import com.colpatria.crudusersapi.user.dto.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  List<User> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
}
