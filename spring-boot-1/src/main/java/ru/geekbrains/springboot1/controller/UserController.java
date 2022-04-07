package ru.geekbrains.springboot1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.springboot1.persist.User;
import ru.geekbrains.springboot1.persist.UserRepository;

import javax.naming.Binding;
import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "user_form";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult binding) {
        if( binding.hasErrors()){
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String delete( @PathVariable("id") long id) {
        userRepository.delete(id);
        return "redirect:/user";
    }
}