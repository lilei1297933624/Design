package com.lyx.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

public class JobLaunch {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:job.xml");
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");

        Job job = (Job) context.getBean("job");
        try {
            // 运行Job
            JobExecution result = launcher.run(job, new JobParameters());
            BatchStatus status = result.getStatus();
            if(Objects.equals(status,BatchStatus.COMPLETED)){
                System.out.println("任务执行成功");
            }else if(Objects.equals(status,BatchStatus.FAILED)){
                System.out.println("任务执行失败");
            }
            // 处理结束，控制台打印处理结果
            System.out.println("jieguo:"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
