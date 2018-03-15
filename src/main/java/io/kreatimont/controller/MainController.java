package io.kreatimont.controller;

import io.kreatimont.model.User;
import io.kreatimont.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/guestbook")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add")
    public String addNewUser(@RequestParam String name, @RequestParam String surname) {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        userRepository.save(user);

        return "Saved";
    }

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path = "/delete")
    public void deleteUser(@RequestParam int id) {
        userRepository.deleteById(id);
    }


}
