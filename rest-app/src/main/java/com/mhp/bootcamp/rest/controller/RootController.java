package com.mhp.bootcamp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RootController {

    @GetMapping
    public List<Link> root() {
        List<Link> links = new ArrayList<>();
        links.add(linkTo(CarController.class).withRel("cars").withType("GET"));
        return links;
    }
}
