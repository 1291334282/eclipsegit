package com.ibm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel("角色实体类")
public class Role {
	@ApiModelProperty("角色id")
	private Integer roleID;
	@ApiModelProperty("角色")
	private String role;
}
