package com.app.Job_Portal.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import com.app.Job_Portal.entities.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    
  
 
}
