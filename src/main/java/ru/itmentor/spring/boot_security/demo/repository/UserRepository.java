package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findByUsername(String username);
}