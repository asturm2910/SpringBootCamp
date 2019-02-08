package com.mhp.bootcamp.helloworld.controller;

import com.mhp.bootcamp.helloworld.domain.Name;
import com.mhp.bootcamp.helloworld.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/names")
public class NameController {

    private NameService service;

    @Autowired
    public NameController(NameService service) {
        this.service = service;
    }

    @GetMapping("/savename/name/{name}/firstname/{firstname}")
    public @ResponseBody
    ResponseEntity<String> saveName(@PathVariable String name, @PathVariable String firstname) {
        System.out.println("Recievd save name call with: " + name + " and " + firstname);
        service.saveName(name, firstname);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }



    @GetMapping()
    public @ResponseBody ResponseEntity<List<Name>> getAll() {
        return new ResponseEntity<List<Name>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public void saveName2(@RequestBody() Name name) {
        service.saveName(name);
    }
}
