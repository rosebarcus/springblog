package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository usersDao;
    private final EmailService emailSVC;

    public PostController(PostRepository postDao, UserRepository usersDao, EmailService emailSVC) {
        this.postDao = postDao;
        this.usersDao = usersDao;
        this.emailSVC = emailSVC;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.getById(id);
        if (currentUser.getId() == post.getUser().getId()) {
            model.addAttribute("post", post);
            return "posts/edit";
        } else {
            return "redirect:/posts/" + id;
        }
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
        post.setUser(usersDao.getById(1L));
        postDao.save(post);
        return "redirect:/posts/" + id;
    }


    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.getById(id);
        if (currentUser.getId() == post.getUser().getId()) {
            postDao.delete(post);
        }
        return "redirect:/posts";
    }

    // When you visit the URL you will see the form to create a post.
    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // When you submit the form on the /posts/create page,
    // the information will be posted to the same URL
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @PostMapping("posts/create")
    public String createPost(@ModelAttribute Post post) {
        post.setUser(usersDao.getById(1L));
        emailSVC.prepareAndSend(post, "hello, this is a test.",
                "Yup you tested it.");
        postDao.save(post);
        return "redirect:/posts";
    }
}