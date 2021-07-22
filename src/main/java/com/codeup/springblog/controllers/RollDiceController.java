package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String viewRollDice(){
        return"roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String viewResult(@PathVariable int n, Model model) {

        int rollDice = (int) Math.floor((Math.random() * 6) + 1);
        boolean check = rollDice == n;
        model.addAttribute("n", n);
        model.addAttribute("rollDice", rollDice);
        model.addAttribute("check", check);
        return "roll-results";
    }
}
