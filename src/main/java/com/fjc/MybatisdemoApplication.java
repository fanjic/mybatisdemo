package com.fjc;

import com.fjc.util.DoQuartz;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisdemoApplication {

    public static void main(String[] args) throws SchedulerException {
        DoQuartz.doSonething();
        SpringApplication.run(MybatisdemoApplication.class, args);
    }

}
