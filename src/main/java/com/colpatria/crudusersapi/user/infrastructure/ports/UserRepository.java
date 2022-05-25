package com.colpatria.crudusersapi.user.infrastructure.ports;

import com.colpatria.crudusersapi.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
