package com.nnk.springboot.service;


import com.nnk.springboot.domain.RuleName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(IllegalArgumentException.class, () -> ruleNameService.findById(id));
    }
}
