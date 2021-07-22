package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    //all posts
    @GetMapping(path = "/posts")
    public String index(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("This is a post1", "This is a post1s body"));
        posts.add(new Post("This is a post 2", "This is a post2s body"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }

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
