package com.yechrom.cloud.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务
 */
@Slf4j
public class GetDataJob extends QuartzJobBean {

    @Autowired
    GetData data;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("~定时任务开始运行~");
        data.run();
    }
}
