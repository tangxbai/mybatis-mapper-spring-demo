package com.viiyue.mapper.spring.demo.bean;

import java.io.Serializable;
import java.util.Date;

import com.viiyue.mapper.spring.demo.enums.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO implements Serializable {

	private static final long serialVersionUID = 3350356660855749547L;

	private Long id;
	private String nickName;
	private String loginName;
	private String password;
	private String remark;
	private Gender gender;
	private Integer age;
	private Boolean display;
	private Date createTime;
	private Date modifyTime;
	private Long version;

}
