# shiziqiu-configuration
ͨ������Ͳ�����
ʵ����һ�����װ�ķֲ�ʽ���ù���ƽ̨��
��һ��
��������ļ�
Windowsϵͳ��C�����һ������
���磺c:/shiziqiu-conf.properties
Linux����
/home/appConfig/shiziqiu-conf.properties

Ȼ������ 
	<dependency>
		<groupId>com.shiziqiu.configuration.core</groupId>
		<artifactId>shiziqiu-configuration-core</artifactId>
		<version>0.0.1</version>
	</dependency>
	
�������ļ���
applicationcontext-XXXXXX.xml
����һ��
<!--���ý����� -->
<bean id="shiziqiuConfPropertyPlaceholderConfigurer" class="com.shiziqiu.configuration.core.spring.ShiZiQiuConfPlaceholderConfigurer" />

Ȼ����ܻ�ȡ ���õ������� ------>>> String redis = ShiZiQiuConfClient.get("redis.port", null);

������ļ��������ڣ�
shiziqiu-configuration-console 
��һ��web��Ŀ��
1����ʼ������  ִ��init��sql
2������jdbc����������  jdbc.properties �ĳ��Լ������ݿ�����
3�����ú�shiziqiu-conf.properties �ĳ��Լ���zookeeper��ַ
4������������Ŀ 
5��Ĭ���û��������� ��admin admin ��������config.properties ���޸�
6�������ڷ����������������  ���飨����  redis �� �������ƣ����� redis���棩
7��Ȼ�������ù������������á�
