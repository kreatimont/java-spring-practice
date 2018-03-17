package io.kreatimont.controller;

import io.kreatimont.model.User;
import io.kreatimont.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "/guestbook")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("name", "kreatimont");
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList()));
        return "users";
    }

}
