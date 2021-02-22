package com.fjc.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

public class DoQuartz {
    public static void doSonething() throws SchedulerException {
        //创建Scheduler对象，调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("t1", "g1")//定义trigger名和组名
                .startNow()//一旦加入scheduler，立即生效，即开始计时
                .withSchedule(CronScheduleBuilder
                .cronSchedule("1-4 0-59 0-23 4 2 ? 2021")).build();

        //定义JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyQuartz.class)
                .withIdentity("j1", "g1").build();

        //调度器中加入JobDetail和Trigger
        scheduler.scheduleJob(jobDetail, trigger);

        //启动人任务调度
        scheduler.start();
    }
}
