package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final UserService userService;
    private final PostService postService;

    public PostPage(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String getComments(Model model, @PathVariable String id) {

        try {
            Post post = postService.find(Long.parseLong(id));
            model.addAttribute("post", post);
            model.addAttribute("comment", new Comment());
            return "PostPage";
        } catch (NumberFormatException e) {
            return "PostPage";
        }

    }


    @PostMapping("/post/{id}")
    public String writeCommentPost(@PathVariable String id,
                                   Model model,
                                   @Valid @ModelAttribute("comment") CommentCredentials comment,
                                   BindingResult bindingResult,
                                   HttpSession httpSession) {


        try {
            Post post = postService.find(Long.parseLong(id));
            model.addAttribute("post", post);
            if (bindingResult.hasErrors()) {
                return "PostPage";
            }

            postService.writeComment(post, comment.getText(), getUser(httpSession));
            putMessage(httpSession, "You published new post");

            return "redirect:/post/" + id;
        } catch (NumberFormatException e) {
            return "PostPage";
        }


    }
}
