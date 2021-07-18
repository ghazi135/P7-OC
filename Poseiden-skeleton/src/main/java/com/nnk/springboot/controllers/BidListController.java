package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    // Done: Inject Bid service


    /**
     * @see BidListService
     */

    @Autowired
    BidListService bidListService;


    /**
     * Bid list home.
     *
     * @param model     the model
     * @return bid list view
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        // DONE: call service find all bids to show to the view
        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidlists", bidLists);
        return "bidList/list";

    }

    /**
     * Add bid form.
     *
     * @return bid list add form view
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {

        return "bidList/add";
    }

    /**
     * Add bid list.
     *
     * @param bid    the bid
     * @param result the result
     * @return either add form view if BindingResult has error or redirect to bid list view if is valid
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return bid list DONE
        if (result.hasErrors()) {

            return "bidList/add";
        }

        bidListService.save(bid);


        return home(model);
    }


    /**
     * Update Bid list form.
     *
     * @param id    the bid list id to update
     * @param model the model
     * @return bid list update form view
     */

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Bid by Id and to model then show to the form DONE

        BidList bidList = bidListService.findById(id);
        model.addAttribute("bidLists", bidList);

        return "bidList/update";
    }
    /**
     * Update bid list.
     *
     * @param id      the bid list id to update
     * @param bidList the bid list
     * @param result  the result
     * @param model   the model
     * @return  update form view if BindingResult has error or redirect to bid list view if is valid
     */

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Bid and return list Bid DONE
        if (result.hasErrors()) {
            return "redirect:/bidList/update/" + id;
        }

        bidListService.save(bidList);
        model.addAttribute("bidListz", bidListService.findAll());

        return "redirect:/bidList/list";
    }

    /**
     * Delete bid list.
     *
     * @param id the bid list id to delete
     * @return redirect to bid list form view
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Bid by Id and delete the bid, return to Bid list Done

        bidListService.delete(id);
        return "redirect:/bidList/list";
    }
}
