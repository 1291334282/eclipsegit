package com.ibm.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
 
@Data
public class BaseResponse {
 
    @ApiModelProperty(value = "返回数据", name = "data", example = "")
    private Object data;
 
}