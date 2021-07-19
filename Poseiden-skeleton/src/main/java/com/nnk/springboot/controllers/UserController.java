package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exeption.UserAlreadyExistingException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
public class UserController {


    /**
     * @see UserService
     */

    @Autowired
    private UserService userService;

    /**
     * User home.
     *
     * @param model the model
     * @return user list view
     */
    @RequestMapping("/user/list")
    public String home(Model model) {

        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        log.info("Loading page :user/list + numbre of users: " + users.size());
        return "user/list";
    }

    /**
     * Add user form.
     *
     * @param bid the model
     * @return user add form view
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {

        return "user/add";
    }

    /**
     * Add user.
     *
     * @param user   the user
     * @param result the result
     * @param model  the model
     * @return  add form view if BindingResult has error or redirect to user list view if is valid
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) throws UserAlreadyExistingException {

        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Update user form.
     *
     * @param id    the user id to update
     * @param model the model
     * @return user update form view
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {


        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Update user.
     *
     * @param id     the user id to update
     * @param user   the user
     * @param result the result
     * @param model  the model
     * @return  update form view if BindingResult has error or redirect to user list view if is valid
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) throws UserAlreadyExistingException {

        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.update(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     * Delete user.
     *
     * @param id the user id to delete
     * @return redirect to user list form view
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        User user = userService.findById(id);
        userService.deleteById(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
