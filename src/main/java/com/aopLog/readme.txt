SpringMVC-AOP切面实现自定义注解日志管理

aspectjrt-XXX.jar ,aspectjweaver-XXX.jar


1，配置文件开启切面注解
	 <!--      开启切面代理 使得spring认识 @Aspect -->
     <aop:aspectj-autoproxy/>
     
     *******据说需要放在spring-mvc.xml配置文件中才能生效
2，自定义注解
	@interface
	
	
3,定义切面
	@Aspect
		--@Pointcut定义切入点
		--@after/@around/@before完成切面任务
		
4，使用自定义注解来切入
		在需要切入的地方使用该注解