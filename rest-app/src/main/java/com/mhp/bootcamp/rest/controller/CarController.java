package com.mhp.bootcamp.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.mhp.bootcamp.rest.repository.Car;
import com.mhp.bootcamp.rest.repository.CarDto;
import com.mhp.bootcamp.rest.service.CarService;
import com.mhp.bootcamp.rest.service.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<Link> saveCar(@RequestBody Car car) {
        Car newCar = service.save(car);
        return new ResponseEntity<Link>(linkTo(methodOn(CarController.class).findById(newCar.getId())).withRel("self").withType("GET"), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ResourceList<CarDto>> findAll() {
        Iterable<Car> cars = service.findAll();
        List<CarDto> resultCars = new ArrayList<>();
        cars.forEach(car -> resultCars.add(new CarDto(car.getId(), car.getName())));
        ResourceList<CarDto> resList = new ResourceList<CarDto>(resultCars);

        resultCars.forEach(dto -> dto.add(linkTo(methodOn(CarController.class).findById(dto.getObjectId())).withRel("self").withType("GET")));
        resultCars.forEach(dto -> dto.add(linkTo(methodOn(CarController.class).deleteById(dto.getObjectId())).withRel("delete").withType("DELETE")));
        resultCars.forEach(dto -> dto.add(linkTo(methodOn(CarController.class).update(null)).withRel("update").withType("DELETE")));

        Link selfLink = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CarController.class).findAll()).withRel("self");
        resList.add(selfLink);

        return new ResponseEntity<ResourceList<CarDto>>(resList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CarDto> findById(@PathVariable(name = "id") long id) {
        Car entity = service.findById(id);
        CarDto result = new CarDto(entity.getId(), entity.getName());
        Collection<? extends GrantedAuthority> auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority auth : auths) {
            SimpleGrantedAuthority sAuth = (SimpleGrantedAuthority) auth;
            if("READ".equals(sAuth.toString())) {
                result.add(linkTo(methodOn(CarController.class).findById(result.getObjectId())).withRel("self").withType("GET"));
            }
            else if ("WRITE".equals(sAuth.toString())) {
                result.add(linkTo(methodOn(CarController.class).deleteById(result.getObjectId())).withRel("delete").withType("DELETE"));
                result.add(linkTo(methodOn(CarController.class).update(null)).withRel("update").withType("PUT"));
            }
        }

        return new ResponseEntity<CarDto>(result, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('WRITE')")
    public void deleteCar(@RequestBody Car car) {
        service.delete(car);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<Object> deleteById(@PathVariable(name="id") long id) {
        service.deleteJById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<Link> update(@RequestBody Car car) {
        Car updatedCar = service.update(car);
        //CarDto resultCar = new CarDto(updatedCar.getId(), updatedCar.getName());
        Link l = linkTo(methodOn(CarController.class).findById(updatedCar.getId())).withRel("self");

        return new ResponseEntity<Link>(l, HttpStatus.OK);
    }
}
