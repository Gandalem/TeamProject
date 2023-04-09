package com.Team_Project.worklog.dto;

import lombok.Data;

@Data
public class WorkLogDTO {
	
	private Long id;
	private String memberName;
    private String title;
    private String content;
    private String createDate;

}
