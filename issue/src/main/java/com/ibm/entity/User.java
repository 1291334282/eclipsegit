package com.ibm.entity;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户实体类")
public class User {
	@ApiModelProperty("用户id")
	private Integer userID;
	@ApiModelProperty("角色id")
	private Integer roleID;
	@ApiModelProperty("用户名")
	private String name;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("注销状态码")
	private String userstate;
	@ApiModelProperty("创建时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registertime;
	@ApiModelProperty("密码")
	private String password;
}
