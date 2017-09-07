package com.shiziqiu.configuration.console.model;
/**
 * 结果
 * @title : ResultT
 * @author : crazy
 * @date : 2017年9月6日 下午8:10:44
 * @Fun :
 */
public class ResultT<T> {

	public static final ResultT<String> SUCCESS = new ResultT<String>(null);
	public static final ResultT<String> FAIL = new ResultT<String>(500, null);
	
	private int code;
	private String msg;
	private T content;
	
	public ResultT(){}
	
	public ResultT(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public ResultT(T content) {
		this.code = 200;
		this.content = content;
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

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ResultT [code=" + code + ", msg=" + msg + ", content="
				+ content + "]";
	}
	
}
