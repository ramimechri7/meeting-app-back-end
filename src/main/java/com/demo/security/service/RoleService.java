package com.demo.security.service;


import com.demo.security.dao.RoleRepository;
import com.demo.security.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Role findById(Integer id){
        return roleRepository.findById(id).get();
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public void delete(Integer id){
        roleRepository.deleteById(id);
    }
}
