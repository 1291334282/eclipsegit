package com.ibm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户角色联合实力类")
public class UserRole extends User {
	@ApiModelProperty("角色")
	private String role;
}
