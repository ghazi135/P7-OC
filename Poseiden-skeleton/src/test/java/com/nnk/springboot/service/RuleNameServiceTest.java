package com.nnk.springboot.service;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest

public class RuleNameServiceTest {
    @Autowired
    private RuleNameService ruleNameService;

    @Test
    public void ruleTest() {
        RuleName rule = new RuleName("aaaa", "bbbb", "Json", "cccc", "xxxx", "yyyy");

        // Save

        ruleNameService.save(rule);
        Assert.assertNotNull(rule.getId());
        Assert.assertTrue(rule.getName().equals("aaaa"));

        // Update
        rule.setName("qqqq");
        ruleNameService.save(rule);
        Assert.assertTrue(rule.getName().equals("qqqq"));

        // Find
        List<RuleName> listResult = ruleNameService.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rule.getId();
        ruleNameService.delete(id);
        assertThrows(IllegalArgumentException.class , () -> ruleNameService.findById(id));
    }
}
