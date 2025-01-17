package com.iview.repository;

import com.iview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByEmail(String email);

    Optional<User> findByUsername(String username);
}
