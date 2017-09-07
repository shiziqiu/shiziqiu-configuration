package com.shiziqiu.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shiziqiu.configuration.core.zk.ShiZiQiuConfClient;
/**
 * ���԰���
 * @title : TestShiZiQiuConfClient
 * @author : crazy
 * @date : 2017��9��7�� ����2:21:41
 * @Fun :
 * ���Լ���xml�����������������
 * <bean id="shiziqiuConfPropertyPlaceholderConfigurer" class="com.shiziqiu.configuration.core.spring.ShiZiQiuConfPlaceholderConfigurer" />
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationcontext-shiziqiu-conf.xml")
public class TestShiZiQiuConfClient {

	@Test
	public void TestShiZiQiuConfClientGet(){
		/**
		 * �������ļ����ȡ���õ�����
		 */
		String redis = ShiZiQiuConfClient.get("redis.port", null);
		System.out.println(redis + "=====redis=====");
	}
	
	
}
