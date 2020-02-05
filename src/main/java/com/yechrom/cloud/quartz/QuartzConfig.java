package com.yechrom.cloud.quartz;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {

    /**
     * 配置定时任务的job
     * @return
     */
    @Bean
    public JobDetail getDataJob() {
        return JobBuilder.newJob(GetDataJob.class).withIdentity("getDataTask").storeDurably().build();
    }

    /**
     * 配置触发器
     * @return
     */
    @Bean
    public Trigger errorInterfaceTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger().forJob(getDataJob()).withIdentity("getDataTask")
                .withSchedule(scheduleBuilder).build();
    }
}
