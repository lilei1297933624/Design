package com.lyx.batch;

import com.lyx.batch.config.PropConf;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

@SpringBootApplication
public class AppMain {

    public static void main(String[] args)
            throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {
//        ConfigurableApplicationContext ctx = SpringApplication.run(AppMain.class, args);
//        PropConf conf = (PropConf) ctx.getBean("propConf");
//        conf.show();
//        @SuppressWarnings("resource")
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                new String[] { "classpath:job.xml" });
//        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
//        Job job = (Job) context.getBean("job");
//        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
//        JobExecution result = launcher.run(job,
//                jobParametersBuilder.toJobParameters());
//        ExitStatus es = result.getExitStatus();
//        if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
//            System.out.println("任务正常完成");
//        } else {
//            System.out.println("任务失败，exitCode=" + es.getExitCode());
//        }

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        calendar.add(Calendar.MONTH,-8);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));

    }
}
