package com.lincy.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lincy.enumeration.RequestMethodEnum;

/**
 * @author lcy79
 * http工具，发送请求以及接收服务器返回结果。
 * http util,send request and recive response.
 */
public class HttpUtil {
	/**
	 * @author lcy79
	 * @param url	请求的url
	 * @param method	请求方式
	 * @param requestBody	requestBody
	 * @return	服务器返回的结果
	 * @throws IOException 
	 */
	public static String doHttp(String url, RequestMethodEnum method, String requestBody) throws IOException {
		//获取url对象
		URL urll = new URL(url);
		//获取http连接对象
		HttpURLConnection conn=(HttpURLConnection)urll.openConnection();
		
		/**
		 * 设置conn属性
		 */
		//允许输出
		conn.setDoOutput(true);
		//允许输入
		conn.setDoInput(true);
		//请求方式
		conn.setRequestMethod(method.name());
		//是否打开缓存
		conn.setUseCaches(false);
		
		//开始连接
		conn.connect();
		
		//发送requestBody
		OutputStream out=conn.getOutputStream();
		out.write(requestBody.getBytes()); 
		out.flush();
		out.close();
		
		//接收响应
		BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		StringBuffer sb=new StringBuffer();
		String line="";
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		reader.close();
		
		//关闭连接
		conn.disconnect();
		
		//返回结果
		return sb.toString();
	}
	
	
}
