package com.enigma.listsong.repositories;

import com.enigma.listsong.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
}
