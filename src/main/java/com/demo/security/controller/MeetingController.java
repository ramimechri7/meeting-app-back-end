package com.demo.security.controller;


import com.demo.security.model.Meeting;
import com.demo.security.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;


    @GetMapping(value = "/meeting/all")
    List<Meeting> findAll(){
        return meetingService.findAll();
    }

    @GetMapping(value = "/meeting/{id}")
    Meeting findById(@PathVariable Integer id){
        return meetingService.findById(id);
    }

    @PostMapping(value = "/meeting/create")
    Meeting save(@RequestBody Meeting meeting){
        return meetingService.save(meeting);
    }

    @DeleteMapping(value = "/meeting/delete/{id}")
    void delete(@PathVariable Integer id){
        meetingService.delete(id);
    }

    @PostMapping(value = "/meeting/edit/{id}")
    void edit(@PathVariable Integer id,@RequestBody Meeting meeting){
        meetingService.edit(id,meeting);
    }

}
