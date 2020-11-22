package com.ibm.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
@ApiModel(value = "返回信息", description = "返回响应信息")
public class ReturnValue extends BaseResponse {
    public static final String STATUS_FAIL = "500";
    public static final String STATUS_OK = "200";
    public static final String MESSAGE_SUCCESS = "操作成功";
    public static final String MESSAGE_ERROR = "操作失敗";
    public static final String ERRCODE_NOERR = "0000";
    public static final String ERRCODE_ERR = "9999";
    //未配置简易流程
    public static final String ERR_SIMPLE_PROCESS = "0001";
    //子目录与主目录的基本编码不匹配
    public static final String ERR_BASIC_CODE = "0002";
    @ApiModelProperty(value = "操作成功或失败标志", name = "status", example = "200")
    protected String status = STATUS_OK;
    @ApiModelProperty(value = "操作错误代码", name = "errcode", example = "5399")
    protected String errcode = "";
    @ApiModelProperty(value = "操作状态信息", name = "message", example = "操作成功")
    protected String message = MESSAGE_SUCCESS;
    @ApiModelProperty(value = "错误信息", name = "errmsg", example = "上送数据错误")
    protected String errmsg = "";
 
    public ReturnValue(String status, String message) {
        this.status = status;
        this.message = message;
    }
 
    public ReturnValue() {
    }
 
    public ReturnValue(String status, String message, JSONObject data, String errcode, String errmsg) {
        super.setData(data);
        this.status = status;
        this.message = message;
    }
 
    public String getErrmsg() {
        return errmsg;
    }
 
    public ReturnValue setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }
 
    public String getMessage() {
        return message;
    }
 
    public ReturnValue setMessage(String message) {
        this.message = message;
        return this;
    }
 
    public String getStatus() {
        return status;
    }
 
    public ReturnValue setStatus(String status) {
        this.status = status;
        return this;
    }
 
    /**
     * description: 返回成功报文无需返回业务数据
     */
    public static ReturnValue ok() {
        ReturnValue returnValue = new ReturnValue();
        returnValue.setData(new JSONObject());
        returnValue.errcode = ERRCODE_NOERR;
        return returnValue;
    }
 
    /**
     * description: 返回成功报文包含返回数据
     */
    public static ReturnValue ok(Object data) {
        ReturnValue response = new ReturnValue();
        response.setStatus(STATUS_OK);
        response.setMessage(MESSAGE_SUCCESS);
        response.setData(data);
        response.setErrmsg("");
        response.setErrcode(ERRCODE_NOERR);
        return response;
    }
 
 
    /**
     * description: 返回成功报文包含返回数据,自定义返回错误信息和错误码
     */
    public static ReturnValue ok(Object data, String errMsg, String errCode) {
        ReturnValue response = new ReturnValue();
        response.setStatus(STATUS_OK);
        response.setMessage(MESSAGE_SUCCESS);
        response.setData(data);
        response.setErrmsg(errMsg);
        response.setErrcode(errCode);
        return response;
    }
 
    /**
     * description: 返回失败报文，自定义错误信息
     */
    public static ReturnValue fail(String errmsg) {
        return fail(errmsg, ERRCODE_ERR);
    }
 
    /**
     * description:返回失败报文，自定义错误代码和错误信息
     */
    public static ReturnValue fail(String errmsg, String errcode) {
        ReturnValue response = new ReturnValue();
        response.setStatus(STATUS_FAIL);
        response.setMessage(MESSAGE_ERROR);
        response.setData(new JSONObject());
        response.setErrmsg(errmsg);
        response.setErrcode(errcode);
        return response;
    }
 
    public String getErrcode() {
        return errcode;
    }
 
    public ReturnValue setErrcode(String errcode) {
        this.errcode = errcode;
        return this;
    }
}