package com.app.Job_Portal.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EducationalDetailsDto {
    private String qualification;

    private String institute;

    private LocalDate admissionDate;

    private LocalDate completionDate;

    private double percentage;

}
