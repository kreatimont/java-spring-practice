package io.kreatimont.controller;

import io.kreatimont.model.User;
import io.kreatimont.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/api_v1")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllUsers() {
        List<User> users =
                StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return Collections.singletonMap("data", users);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map addNewUser(@RequestParam(name = "name") String name, @RequestParam(name = "surname") String surname, @RequestParam(name = "city") String city) {

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setCity(city);

        User savedUser = userRepository.save(user);

        return Collections.singletonMap("data", "saved user: " + savedUser);
    }

}
