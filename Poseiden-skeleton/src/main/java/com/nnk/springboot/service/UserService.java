package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exeption.UserAlreadyExistingException;
import com.nnk.springboot.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@Transactional
public class UserService implements UserDetailsService {

    /**
     * @see UserRepository
     */
    @Autowired
    private UserRepository userRepository;


    /**
     * @see PasswordEncoder
     */



    /**
     * Find all user.
     *
     * @return the user list
     */
    public List<User> findAll() {
        log.info("---------------> find all users" );

        return userRepository.findAll();
    }

    /**
     * Find user by id.
     *
     * @param id the user id
     * @return the user
     */
    public User findById(int id) {

        log.info("---------------> find user by id" + id );
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity doesn't exists"));
    }


    /**
     * Save user.
     *
     * @param user the user to save
     * @return the user saved
     * @throws UserAlreadyExistingException if an user has the same username exists
     */
    public User save(User user) throws UserAlreadyExistingException {

        if (userRepository.existsByUsername(user.getUsername())) {
            log.error("---------------> can not save user" );
            throw new UserAlreadyExistingException("User with username " + user.getUsername() + " is already existing");
        }
        log.info("---------------> save user" );

        return userRepository.save(user);
    }

    /**
     * Update user.
     *
     * @param user the user to update
     * @return the user updated
     */
    public User update(User user) throws UserAlreadyExistingException {

        if (userRepository.existsByUsername(user.getUsername())) {
            log.error("---------------> can not UPDATE user" );
            throw new UserAlreadyExistingException("User with username " + user.getUsername() + " is already existing");
        }
        log.info("---------------> update user" );
        return userRepository.save(user);
    }


    /**
     * Delete user by id.
     *
     * @param id the user id to delete
     */
    public void deleteById(int id) {
        log.info("---------------> delete user by id" + id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("Username " + username + " not found");
        });
    }
}
