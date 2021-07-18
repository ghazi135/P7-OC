package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RuleNameService {

    /**
     * @see RuleNameRepository
     */

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**
     * Find all rule name.
     *
     * @return the rule name list
     */
    public List<RuleName> findAll() {
        log.info("---------------> find  all rule names" );
        return ruleNameRepository.findAll();
    }

    /**
     * Save rule name.
     *
     * @param ruleName the rule name to save
     */

    public void save(RuleName ruleName) {
        log.info("---------------> save rule name" );
        ruleNameRepository.save(ruleName);
    }


    /**
     * Find rule name by id.
     *
     * @param id the rule name id
     * @return the rule name
     */
    public RuleName findById(Integer id) {
        log.info("---------------> find rule name by id" + id );
        return ruleNameRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
    }


    /**
     * Delete rule name by id.
     *
     * @param id the rule name id to delete
     */
    public void delete(Integer id) {
        log.info("---------------> delete rule name by id" + id );
        ruleNameRepository.deleteById(id);
    }
}
