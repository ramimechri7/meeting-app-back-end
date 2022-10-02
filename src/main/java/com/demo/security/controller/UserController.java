package com.demo.security.controller;


import com.demo.security.model.AuthRequest;
import com.demo.security.model.User;
import com.demo.security.service.UserDetailsServiceImpl;
import com.demo.security.service.UserService;
import com.demo.security.util.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UserDetailsServiceImpl userDetailsService;

    public UserController(UserService userService, AuthenticationManager authenticationManager,JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(
            value = "/user/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/authenticate")
    ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);
        System.out.println("Authenticated");
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }


    @GetMapping(value = "/user/{id}")
    ResponseEntity<User> findById (@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(value = "/user/all")
    ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


    @PostMapping(value = "/user/edit/{id}")
    void edit(@PathVariable Integer id, @RequestBody User user){
        userService.edit(id,user);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    void delete(@PathVariable Integer id){
        userService.delete(id);
    }
}
