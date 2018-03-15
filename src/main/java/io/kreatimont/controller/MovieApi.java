package io.kreatimont.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class MovieApi {

    @RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getAllMovies() {
        return Collections.singletonMap("data", "Inception");
    }

}
