package com.app.Job_Portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    private Long skillId;
    private String name;
    private String description;

}
