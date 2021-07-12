package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
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
public class RatingController {
    // DONE: Inject Rating service DONE
    @Autowired
    RatingService    ratingService;
    @Autowired
    RatingRepository ratingRepository;


    @RequestMapping("/rating/list")

    public String home(Model model)
    {
        // DONE: find all Rating, add to model Done
        List<Rating> ratings = ratingService.findAll();
        model.addAttribute("ratingz",ratings);

        return "rating/list";
    }


    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Rating list DONE

        if(result.hasErrors()) {

            return "rating/add";
        }

        ratingService.save(rating);
        home(model);


        return "rating/list";

    }


    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Rating by Id and to model then show to the form DONE

        Rating rating = ratingService.findById(id);

        model.addAttribute("rating", rating);

        return "rating/update";
    }


    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
            BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Rating and return Rating list DONE
        if(result.hasErrors()){
            return "redirect:/rating/update/" + id;
        }
        ratingService.save(rating);
        model.addAttribute("ratingz", ratingService.findAll());


        return "redirect:/rating/list";
    }


    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Rating by Id and delete the Rating, return to Rating list DONE

        ratingService.delete(id);
        return "redirect:/rating/list";
    }
}
