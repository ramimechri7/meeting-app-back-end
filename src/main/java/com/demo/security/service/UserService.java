package com.demo.security.service;


import com.demo.security.dao.UserRepository;
import com.demo.security.model.Meeting;
import com.demo.security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;

    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findById(Integer id){
        return userRepository.findById(id).get();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).get();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public User edit(Integer id, User user){
        User oldUser = userRepository.findById(id).get();
        oldUser = user;
        System.out.println(user);
        return userRepository.save(oldUser);
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }
}
