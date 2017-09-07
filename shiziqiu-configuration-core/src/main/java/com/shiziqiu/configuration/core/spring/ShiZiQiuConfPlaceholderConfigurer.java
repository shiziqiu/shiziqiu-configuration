package com.shiziqiu.configuration.core.spring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.util.StringValueResolver;

import com.shiziqiu.configuration.core.zk.ShiZiQiuConfClient;
import com.shiziqiu.configuration.util.Constant;
/**
 * @title : ShiZiQiuConfPlaceholderConfigurer
 * @author : crazy
 * @date : 2017年9月6日 下午5:11:04
 * @Fun :
 */
public class ShiZiQiuConfPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

	private static Logger logger = LoggerFactory.getLogger(ShiZiQiuConfPlaceholderConfigurer.class);

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		StringValueResolver valueResolver = new StringValueResolver() {

			@Override
			public String resolveStringValue(String strVal) {
				StringBuffer sb = new StringBuffer(strVal);
				/**
				 * 给变量加上${XXXXXXX}
				 */
				boolean start = strVal.startsWith(Constant.PLACEHOLDER_PREFIX);
				boolean end = strVal.endsWith(Constant.PLACEHOLDER_SUFFIX);
				while(start && end) {
					String key = sb.substring(placeholderPrefix.length(), sb.length() - placeholderSuffix.length());
					String zkValue = ShiZiQiuConfClient.get(key, "");
					logger.info(">>>>>>>>>>> resolved placeholder '" + key + "' to value [" + zkValue + "]");
					sb = new StringBuffer(zkValue);
					start = sb.toString().startsWith(placeholderPrefix);
					end = sb.toString().endsWith(placeholderSuffix);
				}
				return sb.toString();
			}
		};
		BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResolver);
		String[] beanNames = beanFactoryToProcess.getBeanDefinitionNames();
		if (null != beanNames && beanNames.length > 0) {
			for (String beanName : beanNames) {
				if(!(beanName.equals(this.beanName)) && beanFactoryToProcess.equals(this.beanFactory)) {
					BeanDefinition bd = beanFactoryToProcess.getBeanDefinition(beanName);
					visitor.visitBeanDefinition(bd);
				}
			}
		}
	}
	
	
	private String beanName;
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
	
	private BeanFactory beanFactory;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
	
	@Override
	public void setIgnoreUnresolvablePlaceholders(boolean ignoreUnresolvablePlaceholders) {
		super.setIgnoreUnresolvablePlaceholders(true);
	}
	
}
