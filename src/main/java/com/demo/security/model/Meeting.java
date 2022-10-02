package com.demo.security.model;


import com.demo.security.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd:HH:mm")
    private Date dateTime;
    private Integer duration;
    private String subject;
    private String location;
    private String description;
    private String invokedEmails;

}
