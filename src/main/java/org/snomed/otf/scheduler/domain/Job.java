package org.snomed.otf.scheduler.domain;

import java.net.URL;
import java.util.*;

public class Job {
	String name;
	String description;
	JobCategory category;
	List<String> parameterNames;
	URL jobRun;
	List<JobSchedule> schedules;
	
	public Job(String name, String description, String[] params) {
		this.name = name;
		this.description = description;
		parameterNames = Arrays.asList(params);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public JobCategory getCategory() {
		return category;
	}
	public void setCategory(JobCategory category) {
		this.category = category;
	}
	public List<String> getParameterNames() {
		return parameterNames;
	}
	public void setParameterNames(List<String> parameterNames) {
		this.parameterNames = parameterNames;
	}
	public URL getJobRun() {
		return jobRun;
	}
	public void setJobRun(URL jobRun) {
		this.jobRun = jobRun;
	}
	public List<JobSchedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<JobSchedule> schedules) {
		this.schedules = schedules;
	}
	@Override
	public boolean equals (Object other) {
		if (other instanceof Job) {
			Job otherJob = (Job)other;
			if (category.equals(otherJob.getCategory())) {
				return name.equals(otherJob.getName());
			}
		}
		return false;
	}


}