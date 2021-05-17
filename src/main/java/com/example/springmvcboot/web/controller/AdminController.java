package com.example.springmvcboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.springmvcboot.web.hiber.service.UserService;
import com.example.springmvcboot.web.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/new")
    public String addUser(ModelMap model) {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", admin);
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("users/{id}")
    public String getUserById(@PathVariable("id") String id, ModelMap model) {
        model.addAttribute("user", userService.getUserByName(id));
        return "admin-user";
    }

//    @GetMapping("users/{id}/edit")
//    public String edit(ModelMap model, @PathVariable("id") String id) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "edit";
//    }

    @PatchMapping("/users")
    public String update(@ModelAttribute("user") @Valid User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users")
    public String delete(@ModelAttribute User user) {
        userService.deleteUserById(user.getId());
        return "redirect:/admin/users";
    }
}
