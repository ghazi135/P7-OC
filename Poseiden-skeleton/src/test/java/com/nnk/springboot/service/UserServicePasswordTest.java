package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exeption.UserAlreadyExistingException;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServicePasswordTest {


    @Test
    public void testPassword() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       assertEquals("$2a$10$E/sQ.1wO38h4i6jzrEdJ4.tYKuii1C2l15GE1XNmq9mWnSs21IVwu",encoder.encode("Azertyuiop123456789-"));

    }
}
