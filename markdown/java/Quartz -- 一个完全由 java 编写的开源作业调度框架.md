# Quartz -- 一个完全由 java 编写的开源作业调度框架

[@Saisimon](http://blog.saisimon.net/)
    
    Quartz 是一个 Java 下作业控制的开源框架。Quartz 用来创建或简单或复杂的调度时间表，执行 Java 下任意数量的作业，Quartz 还支持集群。
> * Quartz 官方下载地址：[Quartz Download](http://www.quartz-scheduler.org/downloads/)
> * Quartz Maven：
>```xml
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.2.2</version>
</dependency>
>```

## Quartz 快速开始
    Quartz 框架整合了许多额外功能,但是使用起来非常的便捷，创建一个类去实现 **org.quartz.Job** 接口。
***
### Quartz 之 HelloWorld

    创建一个 JobTest 类，实现 Job 接口中的 execute 方法
```java
package net.saisimon.test;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobTest implements Job {

    @Override
	public void execute(JobExecutionContext jec) {
	    // 作业的具体操作
		System.out.println("do something , show date : " + new Date());
	}
	
	public static void main(String[] args) {
		try {
		    // 根据 StdSchedulerFactory 创建一个调度器实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// 创建作业实例
			JobDetail job = JobBuilder.newJob(JobTest.class)
					.withIdentity("job", "group")
				    .build();
		    // 获取当前时间的下一分钟的时间
		    // 例如当前时间为 2016-04-07 16:13:40 ,这获取的时间为 2016-04-07 16:14:00
			Date runTime = DateBuilder.evenMinuteDate(new Date());
			// 创建触发器实例，将 runTime 设置为触发器时间。
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger", "group")
					.startAt(runTime)
				    .build();
			// 将作业与触发器添加到调度器中
			scheduler.scheduleJob(job, trigger);
			// 启动调度器
			scheduler.start();
			// 当前线程休眠60秒
			Thread.sleep(60000);
			// 关闭调度器
			scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```
    这样就完成了一个作业调度的过程。调度器会在 runTime 时候触发作业运行，打印出："do something , show date : Thu Apr 07 16:16:00 CST 2016"。
***
### Quartz 之定时调度
    使用 Quartz 实现定时调度功能，修改 JobTest 类：
```java
package net.saisimon.test;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobTest implements Job {
	
	@Override
	public void execute(JobExecutionContext jec) {
		System.out.println("do something , show date : " + new Date());
	}
	
	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			JobDetail job = JobBuilder.newJob(JobTest.class)
					.withIdentity("job", "group")
				    .build();
			// 使用 cron 表达式来规定什么时候触发调度器执行作业
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger", "group")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
				    .build();
			scheduler.scheduleJob(job, trigger);
			// 当前线程休眠10秒
			Thread.sleep(10000);
			scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```
    触发器会每两秒执行一次调度作业，打印出：
    do something , show date : Thu Apr 07 16:41:32 CST 2016
    do something , show date : Thu Apr 07 16:41:34 CST 2016
    do something , show date : Thu Apr 07 16:41:36 CST 2016
    do something , show date : Thu Apr 07 16:41:38 CST 2016
    do something , show date : Thu Apr 07 16:41:40 CST 2016
***
### Quartz 之 cron 表达式
* cron 表达式字段说明
| 字段          | 允许的值         | 允许的特殊字符     |
| ------------- |:----------------:| ------------------:|
| 秒            | 0-59             | , - * /            |
| 分            | 0-59             | , - * /            |
| 小时          | 0-23             | , - * /            |
| 日            | 1-31             | , - * ? / L W C    |
| 月            | 1-12             | , - * /            |
| 周            | 1-7              | , - * ? / L C #    |
| 年            | empty, 1970-2099 | , - * /            |
***
* 一些常用的 cron 表达式
```properties
# Per Second 每秒执行一次
# 2016/4/5 16:48:42 
# 2016/4/5 16:48:43
job_task=* * * * * ? *

# Per 30 Second 每30秒执行一次
# 2016/4/5 16:51:30
# 2016/4/5 16:52:00
job_task=0/30 * * * * ? *

# Per Minute 每分钟执行一次
# 2016/4/5 16:49:00 
# 2016/4/5 16:50:00
job_task=0 * * * * ? *

# Per 5 Minute 每五分钟执行一次
# 2016/4/5 16:55:00
# 2016/4/5 17:00:00
job_task=0 0/5 * * * ? *

# Per Hour 每小时执行一次
# 2016/4/5 17:00:00 
# 2016/4/5 18:00:00
job_task=0 0 * * * ? *

# Per 2 Hour 每2小时执行一次
# 2016/4/5 18:00:00 
# 2016/4/5 20:00:00
job_task=0 0 0/2 * * ? *

# Per Day 每天执行一次
# 2016/4/6 0:00:00 
# 2016/4/7 0:00:00
job_task=0 0 0 * * ? *

# Per Month 每月执行一次
# 2016/5/1 0:00:00 
# 2016/6/1 0:00:00
job_task=0 0 0 1 * ? *

# Per Year 每年执行一次
# 2017/1/1 0:00:00 
# 2018/1/1 0:00:00
job_task=0 0 0 1 1 ? *
```
***
### Quartz 之 Spring 整合
    Spring 进一步简化了 Quartz 的配置。不过需要注意的是 Spring 3.2.4 以下不支持 Quartz 2.x，需要使用 Quartz 1.x 。
    
    确保未配置 Quartz 时 Spring 能正常运行。
    
* Spring 配置
```xml
    <!-- 启动触发器的配置 -->
    <bean name="startQuertz" lazy-init="false" autowire="no" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <list>
                <ref bean="jobTrigger" />
            </list>
        </property>
    </bean>
 
    <!-- quartz 触发器配置 -->
    <bean id="jobTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="jobDetail" />
        <property name="cronExpression" value="0 0 0 * * ? *" />
    </bean>
 
    <!-- job 配置 -->
    <bean id="jobDetail" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <property name="targetObject" ref="jobImpl" />
        <property name="targetMethod" value="doSomething" />
    </bean>
    
    <!-- 作业实体类 -->
    <bean id="jobImpl" class="net.saisimon.dao.impl.JobImpl" />
```
* jobImpl 类
```java
package net.saisimon.dao.impl;

import net.saisimon.core.util.DateUtil;

public class JobImpl {
	
	public void doSomething() {
		System.out.println("do something " + new Date());
	}
	
}
```
    调度器会每秒钟触发一次，执行 JobImpl 类的 doSomething 方法。打印出：
    do something Thu Apr 07 22:36:32 CST 2016
    do something Thu Apr 07 22:36:33 CST 2016
    do something Thu Apr 07 22:36:34 CST 2016
    ...

***
## 参考资料
* [Quartz 官网](http://www.quartz-scheduler.org/ )
* [Quartz wiki](https://zh.wikipedia.org/wiki/Quartz_(%E6%A1%86%E6%9E%B6) )
* [spring 4.1.6 配置 quartz 2.2.1 （maven）](http://my.oschina.net/ydsakyclguozi/blog/465179)