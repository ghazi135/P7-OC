package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exeption.UserAlreadyExistingException;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService  implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new EntityNotFoundException("Entity doesn't exists"));
    }


    public User save(User user) throws UserAlreadyExistingException {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistingException("User with username " + user.getUsername() + " is already existing");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user) throws UserAlreadyExistingException {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistingException("User with username " + user.getUsername() + " is already existing");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                             .orElseThrow(() -> {
                                 return new UsernameNotFoundException(
                                         "Username " + username + " not found");
                             });
    }
}
