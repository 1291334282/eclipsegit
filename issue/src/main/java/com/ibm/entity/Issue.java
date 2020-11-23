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
	private String createtime;

	@ApiModelProperty("到创建时间1") // 时间查询需用
	private String createtime1;

	@ApiModelProperty("Issue类型")
	private String type;
	@ApiModelProperty("Issue等级")
	private String level;
	@ApiModelProperty("影响版本")
	private String version;
	@ApiModelProperty("计划修改完成时间")
	private String plantime;

	@ApiModelProperty("到计划修改完成时间1") // 时间查询需用
	private String plantime1;

	@ApiModelProperty("实际完成时间")
	private String acttime;
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
	// ---------------------------

}
