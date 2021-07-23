package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    //all posts
    @GetMapping(path = "/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
       model.addAttribute("post", postDao.getById(id));
        return "posts/show";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        post.setTitle();
        return "redirect:/posts/" + id;

    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getById(id));
        return "posts/edit";
    }


    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, @RequestParam String title, @RequestParam String body,
                             Model model) {
        Post post = postDao

        postDao.delete(postDao.getById(id));
        return "redirect:/posts" + id;
    }

    @PostMapping("/posts/save/edit/{id}")
    public String editOne(Model model,@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post post = postDao.getById(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts/" + post.getId();
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
