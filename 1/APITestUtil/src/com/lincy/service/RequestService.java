package com.lincy.service;

import com.lincy.entity.ResultEntity;

/**
 * @author lcy79
 * 请求方法接口,每个方法代标不同的method
 */
public interface RequestService {
	
	/**
	 * 执行请求
	 * @param url	请求的url
	 * @param method	请求的方式
	 * @param requestBody	请求的数据
	 * @param times	请求的次数
	 * @return ResultEntity
	 */
	ResultEntity request(String url,String method, String requestBody,String times);
	
}
