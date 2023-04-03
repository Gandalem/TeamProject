package com.Team_Project.entity;

import lombok.Getter;

@Getter
public enum MemberType {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");
    
	MemberType(String value) {
		this.value = value;
	}

	private String value;
}
