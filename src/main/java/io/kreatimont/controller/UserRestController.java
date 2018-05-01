package io.kreatimont.controller;

import io.kreatimont.model.User;
import io.kreatimont.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/api_v1")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllUsers() {
        List<User> users =
                StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return Collections.singletonMap("data", users);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map addNewUser(@RequestParam(name = "email") String email,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "name") String name,
                          @RequestParam(name = "surname", required = false) String surname,
                          @RequestParam(name = "city", required = false) String city,
                          @RequestParam(name = "country", required = false) String country,
                          @RequestParam(name = "phone_number", required = false) String phone_number,
                          @RequestParam(name = "role", required = false) String role,
                          @RequestParam(name = "bday", required = false) String bday) {

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        if(city != null && !city.isEmpty()) {
            user.setCity(city);
        }


        userRepository.save(user);
        Map<String, Object> response = new HashMap<>();

        response.put("data", user);
        response.put("success", "true");

        return response;
    }

    @RequestMapping(value = "/users/{id:[\\d]+}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map deleteUser(@PathVariable int id) {

        Optional<User> userToDelete = userRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if(userToDelete.isPresent()) {
            userRepository.deleteById(id);
            response.put("data", "user with id " + id + " successfuly deleted.");
            response.put("success", "true");
        } else {
            response.put("data", "user with id " + id + " not exist.");
            response.put("success", "false");
        }

        return response;
    }

    @RequestMapping(value = "/users/{id:[\\d]+}", method = {RequestMethod.POST, RequestMethod.PUT}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map updateUser(@PathVariable int id,
                          @RequestParam(name = "email", required = false) String email,
                          @RequestParam(name = "password", required = false) String password,
                          @RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "surname", required = false) String surname,
                          @RequestParam(name = "city", required = false) String city,
                          @RequestParam(name = "country", required = false) String country,
                          @RequestParam(name = "phone_number", required = false) String phone_number,
                          @RequestParam(name = "role", required = false) String role,
                          @RequestParam(name = "bday", required = false) String bday) {

        Optional<User> userToUpdate = userRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if(userToUpdate.isPresent()) {

            User safeUser = userToUpdate.get();

            if(name != null && !name.isEmpty()) {
                safeUser.setName(name);
            }

            if(email != null && !email.isEmpty()) {
                safeUser.setEmail(email);
            }

            userRepository.save(safeUser);

            response.put("data", safeUser);
            response.put("success", "true");
        } else {
            response.put("data", "user with id " + id + " not exist.");
            response.put("success", "false");
        }

        return response;
    }

}
