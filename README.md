# shiziqiu-configuration
通过借鉴和查资料
实现了一个简易版的分布式配置管理平台，
第一步
添加配置文件
Windows系统在C盘添加一个配置
例如：c:/shiziqiu-conf.properties
Linux如下
/home/appConfig/shiziqiu-conf.properties

然后引入 
	<dependency>
		<groupId>com.shiziqiu.configuration.core</groupId>
		<artifactId>shiziqiu-configuration-core</artifactId>
		<version>0.0.1</version>
	</dependency>
	
在配置文件里
applicationcontext-XXXXXX.xml
加上一句
<!--配置解析器 -->
<bean id="shiziqiuConfPropertyPlaceholderConfigurer" class="com.shiziqiu.configuration.core.spring.ShiZiQiuConfPlaceholderConfigurer" />

然后就能获取 配置的数据了 ------>>> String redis = ShiZiQiuConfClient.get("redis.port", null);

上面的文件是配置在：
shiziqiu-configuration-console 
是一个web项目：
1、初始化数据  执行init。sql
2、配置jdbc的连接数据  jdbc.properties 改成自己的数据库链接
3、配置好shiziqiu-conf.properties 改成自己的zookeeper地址
4、启动部署项目 
5、默认用户名和密码 （admin admin ）可以在config.properties 里修改
6，首先在分组管理里新增分组  分组（例如  redis ） 分组名称（例如 redis缓存）
7、然后在配置管理里新增配置。
