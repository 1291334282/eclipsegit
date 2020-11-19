package com.ibm.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Issue实体类")
public class Issue {
	// 创建issue
	@ApiModelProperty("Issue题目")
	private String title;
	@ApiModelProperty("Issue.No")
	private Integer issueID;
	@ApiModelProperty("创建时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	@ApiModelProperty("Issue类型")
	private String type;
	@ApiModelProperty("Issue等级")
	private String level;
	@ApiModelProperty("影响版本")
	private String version;
	@ApiModelProperty("计划修改完成时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date plantime;
	@ApiModelProperty("实际完成时间")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date acttime;
	@ApiModelProperty("重现步骤")
	private String step;
	@ApiModelProperty("指派修改人ID")
	private Integer userID;
	// --------------------------
	@ApiModelProperty("创建人")
	private String creater;
	@ApiModelProperty("Issue状态")
	private String issuestate;
	@ApiModelProperty("解决方案")
	private String solution;
	
//	private User user;//用于封装User信息
}
