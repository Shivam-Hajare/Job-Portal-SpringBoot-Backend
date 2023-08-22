package com.app.Job_Portal.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Job_Portal.dto.EducationalDetailsDto;
import com.app.Job_Portal.dto.JobListDto;
import com.app.Job_Portal.dto.JobSeekerResponseDto;
import com.app.Job_Portal.dto.RecruiterDto;
import com.app.Job_Portal.dto.SkillDto;
import com.app.Job_Portal.entities.Skill;
import com.app.Job_Portal.repository.SkillRepository;


@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<SkillDto>getAllSkills()
	{
		List<Skill>allSkills=skillRepo.findAll();
		
		return allSkills.stream().map((skills)->{
			SkillDto allSkillsHolder=mapper.map(skills, SkillDto.class);
			
			return allSkillsHolder;
		}).collect(Collectors.toList());
		
		
		/*return recruiters.stream().map((recruiter)->{
			RecruiterDto recruiterHolder=mapper.map(recruiter,RecruiterDto.class);
			
			JobListDto jobList=mapper.map(recruiter.getJobListings(),JobListDto.class);
			recruiterHolder.setJobListing(jobList);
			return recruiterHolder;
		}).collect(Collectors.toList());
		 * 
		 * 
		 * 
		 */
		
	}
}
