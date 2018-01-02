package com.lincy.service.impl;

import java.io.IOException;

import com.lincy.core.HttpUtil;
import com.lincy.entity.ResultEntity;
import com.lincy.enumeration.RequestMethodEnum;
import com.lincy.service.RequestService;

/**
 * @author lcy79
 * 请求方法实现类
 */
public class RequestServiceImpl implements RequestService{

	/* (non-Javadoc)
	 * @see com.lincy.service.RequestService#request(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultEntity request(String url, String method, String requestBody, String times) {
		ResultEntity res = new ResultEntity();
		try {
			long begintime = System.nanoTime();
			StringBuffer result = new StringBuffer();
			for(int i = 0; i < Integer.parseInt(times); i++){
				result.append("第"+i+"次请求结果："+HttpUtil.doHttp(url, RequestMethodEnum.valueOf(method), requestBody)+"\r\n");
			}
			//运算代码
			long endtime = System.nanoTime();
			double costTime = (endtime - begintime)/1000000d;
			res.setResult(result.toString());
			res.setDuringTime(String.valueOf(costTime));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
