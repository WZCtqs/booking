package com.zih.booking.system.vo;

import com.zih.booking.system.enums.ResultStatusCode;

import java.io.Serializable;

/**
 * 接口返回数据模型
 * @author
 *
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	private int code;

    private String msg = "success";

    private T data;

	public Result(int code,String msg,T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public Result(T data){
		this.code = SUCCESS;
		this.data = data;
	}
	public Result(){
		this.code = SUCCESS;
	}
	public Result(Boolean b){
		if(b){
			this.code = SUCCESS;
		}else {
			this.code = FAIL;
		}
	}

	public Result(ResultStatusCode resultStatusCode, T data){
		this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
	}

	public Result(int code, String msg){
		this(code, msg, null);
	}

	public Result(ResultStatusCode resultStatusCode){
		this(resultStatusCode, null);
	}

    
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
    
    
}
