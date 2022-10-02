package com.demo.security.controller;


import com.demo.security.model.Role;
import com.demo.security.model.User;
import com.demo.security.service.RoleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping(
            value = "/role/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Role> save(@RequestBody Role role){
        return ResponseEntity.ok(roleService.save(role));
    }


    @GetMapping(value = "/role/{id}")
    ResponseEntity<Role> findById (@PathVariable Integer id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping(value = "/role/all")
    ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @DeleteMapping(value = "/role/delete/{id}")
    void delete(@PathVariable Integer id){
        roleService.delete(id);
    }
}
