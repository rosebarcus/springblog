package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    //all posts
    @GetMapping(path = "/posts")
    public String index(Model model) {
        model.addAttribute("posts")
        return "posts/index";
    }
    //todo create a new post object and pass it to the view
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postByID(@PathVariable long id) {
        return "../posts/show.html";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createForm() {
        return "View form to create a post";
    }

    @RequestMapping(path = "/post/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost( ){
        return "Create a new post";
    }




}
