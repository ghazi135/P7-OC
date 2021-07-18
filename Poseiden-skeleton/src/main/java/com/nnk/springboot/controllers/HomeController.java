package com.nnk.springboot.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    /**
     * Home.
     *
     * @return the home view
     */

    @RequestMapping("/")
    public String home(Model model) {

        return "home";
    }

    /**
     * Admin home.
     *
     * @return redirect to the bid list view
     */

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin/home")
    public String adminHome(Model model) {

        return "redirect:/bidList/list";
    }


}
