package com.ibm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("IssueUser联合实体类")
public class IssueUser extends Issue {
	@ApiModelProperty("用户表的用户名")
	private String name;
	@ApiModelProperty("角色id")
	private Integer roleID;
	@ApiModelProperty("创建issue数")
	private Integer creation;
	@ApiModelProperty("收到issue数")
	private Integer recived;
	@ApiModelProperty("修改issue数")
	private Integer resolved;
	@ApiModelProperty("完成率")
	private String completion;
}
