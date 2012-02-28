package com.ztoole.test;

import com.ztools.rmi.ConfigService;


public class TestRmiServer {
	/**
	 * 启动 RMI 注册服务并进行对象注册
	 */
	public static void main(String[] argv) {
		ConfigService.startRmi();
	}
}
