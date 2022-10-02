package com.demo.security.service;


import com.demo.security.dao.MeetingRepository;
import com.demo.security.filter.JwtTokenFilter;
import com.demo.security.model.Meeting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;
    private UserService userService;
    private JwtTokenFilter jwtTokenFilter;
    public MeetingService(MeetingRepository meetingRepository, UserService userService, JwtTokenFilter jwtTokenFilter){
        this.meetingRepository = meetingRepository;
        this.userService = userService;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    public Meeting findById(Integer id){
        return meetingRepository.findById(id).get();
    }

    public List<Meeting> findAll(){
            return meetingRepository.findAll();
    }


    @Transactional
    public Meeting save(Meeting meeting){
        meeting.setCreator(this.jwtTokenFilter.currentUsername);
        meeting.setInvokedEmails(userService.findByUsername(this.jwtTokenFilter.currentUsername).getEmail()+";"+meeting.getInvokedEmails());
        String[] emails = meeting.getInvokedEmails().split(";");

        return meetingRepository.save(meeting);
    }

    public Meeting edit(Integer id, Meeting meeting){
        Meeting oldMeeting = meetingRepository.findById(id).get();
        oldMeeting = meeting;
        return meetingRepository.save(oldMeeting);
    }

    public void delete(Integer id){
        meetingRepository.deleteById(id);
    }

}
