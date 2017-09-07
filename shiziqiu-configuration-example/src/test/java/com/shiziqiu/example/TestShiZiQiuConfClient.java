package com.shiziqiu.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shiziqiu.configuration.core.zk.ShiZiQiuConfClient;
/**
 * 测试案例
 * @title : TestShiZiQiuConfClient
 * @author : crazy
 * @date : 2017年9月7日 下午2:21:41
 * @Fun :
 * 在自己的xml最上面添加下面配置
 * <bean id="shiziqiuConfPropertyPlaceholderConfigurer" class="com.shiziqiu.configuration.core.spring.ShiZiQiuConfPlaceholderConfigurer" />
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationcontext-shiziqiu-conf.xml")
public class TestShiZiQiuConfClient {

	@Test
	public void TestShiZiQiuConfClientGet(){
		/**
		 * 从配置文件里获取配置的数据
		 */
		String redis = ShiZiQiuConfClient.get("redis.port", null);
		System.out.println(redis + "=====redis=====");
	}
	
	
}
