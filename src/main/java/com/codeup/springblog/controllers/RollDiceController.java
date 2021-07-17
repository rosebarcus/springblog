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

        int rollDice = (int) (Math.random() * 6 + 1);
        String message;

        if (rollDice == n) {
            message = "You guessed it!";
        } else {
            message = "Sorry, that wasn't the number. Try again!";
        }

        model.addAttribute("n", n);
        model.addAttribute("rollDice", rollDice);
        model.addAttribute("message", message);

        return "roll-results";
    }
}
