package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The interface User repository.
 */
@Repository

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Exists by username.
     *
     * @param username the username
     * @return  true if one User with this username exists or false
     */
    boolean existsByUsername(String username);



    /**
     * Find by username.
     *
     * @param username the username
     * @return the User
     */
    Optional<User> findByUsername(String username);
}
