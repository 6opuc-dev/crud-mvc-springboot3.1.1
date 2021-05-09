package com.example.springmvcboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.springmvcboot.web.hiber.service.UserService;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/users")
//    public String getUsers(ModelMap model) {
//        model.addAttribute("users", userService.getUsers());
//        return "users";
//    }

//    @GetMapping("/users/new")
//    public String addUser(ModelMap model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }

//    @PostMapping("/users")
//    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "new";
//        }
//        userService.addUser(user);
//        return "redirect:/users";
//    }

    @GetMapping("users/{id}")
    public String getUserById(@PathVariable("id") String id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

//    @GetMapping("users/{id}/edit")
//    public String edit(ModelMap model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "edit";
//    }

//    @PatchMapping("users/{id}")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @PathVariable("id") Long id) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
//        userService.updateUser(id, user);
//        return "redirect:/users";
//    }

//    @DeleteMapping("users/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/users";
//    }
}
