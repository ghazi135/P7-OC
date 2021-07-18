package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {


    // DONE: Inject Trade service

    /**
     * @see TradeService
     */
    @Autowired
    TradeService tradeService;

    /**
     * Trade home.
     *
     * @param model     the model
     * @return redirect to trade list view
     */

    @RequestMapping("/trade/list")
    public String home(Model model) {
        // DONE: find all Trade, add to model DONE
        List<Trade> trades = tradeService.findAll();
        model.addAttribute("tradez", trades);

        return "trade/list";
    }


    /**
     * Add rating form.
     *
     * @param trade the model
     * @return trade add form view
     */

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {

        return "trade/add";
    }


    /**
     * Add trade.
     *
     * @param trade  the trade
     * @param result the result
     * @return  add form view if BindingResult has error or redirect to trade list view if is valid
     */

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // DONE: check data valid and save to db, after saving return Trade list DONE
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.save(trade);
        home(model);

        return "trade/list";
    }


    /**
     * Update trade form.
     *
     * @param id    the trade id to update
     * @param model the model
     * @return trade update form view
     */

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // DONE: get Trade by Id and to model then show to the form DONE

        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);

        return "trade/update";
    }


    /**
     * Update trade.
     *
     * @param id     the trade id to update
     * @param trade  the trade
     * @param result the result
     * @return either update form view if BindingResult has error or redirect to trade list view if is valid
     */

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
        // DONE: check required fields, if valid call service to update Trade and return Trade list DONE
        if (result.hasErrors()) {
            return "redirect:/trade/update/" + id;
        }
        tradeService.save(trade);
        model.addAttribute("tradez", tradeService.findAll());

        return "redirect:/trade/list";
    }

    /**
     * Delete trade.
     *
     * @param id the trade id to delete
     * @return redirect to trade list form view
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // DONE: Find Trade by Id and delete the Trade, return to Trade list

        tradeService.delete(id);

        return "redirect:/trade/list";
    }
}
