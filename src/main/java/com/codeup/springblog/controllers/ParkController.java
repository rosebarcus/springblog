package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Park;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParkController {
    @GetMapping("/parks")
    public String showParks(Model model) {
        Park yo = new Park("Yosemite");
        Park gc = new Park("Grand Canyon");

        List<Park> parks = new ArrayList<>();

        parks.add(yo);
        parks.add(gc);

        model.addAttribute("parks", parks);
        return "parks";
    }
}
