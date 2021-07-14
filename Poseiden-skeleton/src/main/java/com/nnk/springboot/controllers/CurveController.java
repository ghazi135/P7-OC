package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
public class CurveController {
    // DONE: Inject Curve Point service
    @Autowired
    CurvePointService curvePointService;



    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        // DONE: find all Curve Point, add to model :DONE
        List<CurvePoint> curvePoints = curvePointService.findAll();

        model.addAttribute("curvePoint", curvePoints);

        return"curvePoint/list";
    }


    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint) {

        return "curvePoint/add";
    }


    @PostMapping("/curvePoint/validate")
    public String validate(@Valid  CurvePoint curvePoint, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Curve list
//        if (result.hasErrors()) {
//
//            return "curvePoint/add";
//        }

        curvePointService.save(curvePoint);
        home(model);

        return "curvePoint/list";
    }


    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get CurvePoint by Id and to model then show to the form ...

        CurvePoint curvePoint = curvePointService.findById(id);

        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }


    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
            BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Curve and return Curve list ..

//        if (result.hasErrors()) {
//
//            return "redirect:/curvePoint/update/" + id;
//        }

        curvePointService.save(curvePoint);
        model.addAttribute("curvePointaz", curvePointService.findAll());

        return "redirect:/curvePoint/list";

    }


    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Curve by Id and delete the Curve, return to Curve list..

        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
