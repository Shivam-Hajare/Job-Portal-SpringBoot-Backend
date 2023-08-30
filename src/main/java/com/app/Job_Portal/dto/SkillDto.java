package com.app.Job_Portal.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class SkillDto {
	
    private Long skillId;
    
    @NotNull
    @NotBlank(message="Please enter Skill name")
    @Size(max =20,message ="Skill name should not be greater than 20 character")
    private String name;
    
    @NotNull
    @NotBlank(message="Please enter Skill description")
    @Size(max =20,message ="Skill description should not be greater than 20 character")
    private String description;

}
