package com.varxyz.eka.auth.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EkaUser {
	
	private long eid;
	private String userId;
	private String userPw;
	private String userPw2;
	private String name;
	private String gender;
	private String ssn;
	private String phone;
	private String email;
	private String regDate;
}
