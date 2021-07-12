package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class RuleNameController {

    @Autowired
    RuleNameService ruleNameService;


    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // DONE: find all RuleName, add to model DONE
        List<RuleName> ruleNames = ruleNameService.findAll();
        model.addAttribute("ruleNamez",ruleNames);


        return "ruleName/list";
    }


    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {

        return "ruleName/add";
    }


    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return RuleName list DONE
        if(result.hasErrors()){

            return "ruleName/add";
        }
        ruleNameService.save(ruleName);
        home(model);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get RuleName by Id and to model then show to the form DONE

        RuleName ruleName = ruleNameService.findById(id);

        model.addAttribute("ruleName", ruleName);

        return "ruleName/update";
    }


    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
            BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update RuleName and return RuleName list
        if(result.hasErrors()){
            return "redirect:/ruleName/update/" + id;
        }
        ruleNameService.save(ruleName);
        model.addAttribute("ruleNamez", ruleNameService.findAll());

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // DONE: Find RuleName by Id and delete the RuleName, return to Rule list Done

        ruleNameService.delete(id);
        return "redirect:/ruleName/list";
    }
}
