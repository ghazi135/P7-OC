package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {

    // DONE: Inject Curve Point service

    /**
     * @see CurvePointService
     */

    @Autowired
    CurvePointService curvePointService;


    /**
     * Curve Point home.
     *
     * @param model     the model
     * @return redirect to curve point list view
     */

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        // DONE: find all Curve Point, add to model :DONE
        List<CurvePoint> curvePoints = curvePointService.findAll();

        model.addAttribute("curvePoint", curvePoints);

        return "curvePoint/list";
    }

    /**
     * Add curve point
     *
     * @param curvePoint the curve point
     * @return curve point add form view
     */

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint) {

        return "curvePoint/add";
    }

    /**
     * Add curve point.
     *
     * @param curvePoint the curve point
     * @param result     the result
     * @return  add form view if BindingResult has error or redirect to curve point list view if is valid
     */

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Curve list
        //        if (result.hasErrors()) {
        //
        //            return "curvePoint/add";
        //        }

        curvePointService.save(curvePoint);
        home(model);

        return "curvePoint/list";
    }

    /**
     * Update Curve point form.
     *
     * @param id    the curve point id to update
     * @param model the model
     * @return curve point update form view
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get CurvePoint by Id and to model then show to the form ...

        CurvePoint curvePoint = curvePointService.findById(id);

        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }


    /**
     * Update curve point.
     *
     * @param id         the curve point id to update
     * @param curvePoint the curve point
     * @param result     the result
     * @param model      the model
     * @return  update form view if BindingResult has error or redirect to curve point list view if is valid
     */

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Curve and return Curve list ..

        //        if (result.hasErrors()) {
        //
        //            return "redirect:/curvePoint/update/" + id;
        //        }

        curvePointService.save(curvePoint);
        model.addAttribute("curvePointaz", curvePointService.findAll());

        return "redirect:/curvePoint/list";

    }

    /**
     * Delete curve point.
     *
     * @param id the curve point id to delete
     * @return redirect to curve point list form view
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Curve by Id and delete the Curve, return to Curve list..

        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
