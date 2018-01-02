package com.lincy.factory;

/**
 * @author lcy79
 * 代理类 还没实现
 */
public class Proxy {
	public void test() {
		long begintime = System.nanoTime();
		//运算代码
		long endtime = System.nanoTime();
		long costTime = (endtime - begintime)/1000;
	}
}
