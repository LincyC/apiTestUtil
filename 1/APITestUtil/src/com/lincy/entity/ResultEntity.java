package com.lincy.entity;

/**
 * @author lcy79
 * 结果实体,请求后服务器返回的结果以及请求响应所花费的时长
 */
public class ResultEntity {
	//结果
	private String result;
	//持续时长
	private String duringTime;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDuringTime() {
		return duringTime;
	}
	public void setDuringTime(String duringTime) {
		this.duringTime = duringTime;
	}
	
	
}
