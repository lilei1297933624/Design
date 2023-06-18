//package com.lyx.batch;
//
//import cn.hutool.core.text.csv.CsvUtil;
//import cn.hutool.core.text.csv.CsvWriter;
//import cn.hutool.core.util.CharsetUtil;
//import cn.hutool.core.util.RandomUtil;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author hongtao.hao
// * @date 2019/7/3
// */
//@Configuration
//@EnableBatchProcessing
//public class JobSplitDemo3 {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//
//
//
//
//    @Bean
//    public Step jobSplitStep1() {
//        return stepBuilderFactory.get("jobSplitStep1").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
//                System.out
//                        .println(chunkContext.getStepContext().getStepName() + "," + Thread.currentThread().getName());
//                Thread.sleep(3000);
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step jobSplitStep2() {
//        return stepBuilderFactory.get("jobSplitStep2").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
//                System.out
//                        .println(chunkContext.getStepContext().getStepName() + "," + Thread.currentThread().getName());
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Step jobSplitStep3() {
//        return stepBuilderFactory.get("jobSplitStep3").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
//                System.out
//                        .println(chunkContext.getStepContext().getStepName() + "," + Thread.currentThread().getName());
//                Thread.sleep(5000);
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }
//
//    @Bean
//    public Flow jobSplitFLow1() {
//        return new FlowBuilder<Flow>("jobSplitFLow1").start(jobSplitStep1()).build();
//    }
//
//    @Bean
//    public Flow jobSplitFLow2() {
//        return new FlowBuilder<Flow>("jobSplitFLow2")
//                .start(jobSplitStep2())
//                .next(jobSplitStep3())
//                .build();
//    }
//
//    @Bean
//    Job jobSplitJob2() {
//        return jobBuilderFactory.get("jobSplitJob2"+new Date()).start(jobSplitStep1()).next(jobSplitStep2())
//                .next(jobSplitStep3()).build();
//    }
//
////    @Bean
////    Job jobSplitJob() {
////        return jobBuilderFactory.get("jobSplitJob1"+new Date()).incrementer(new RunIdIncrementer())
////                .start(jobSplitFLow1())
////                //TODO 利用split完成 jobSplitFLow1 和 jobSplitFLow2 并发（同时）执行
////                .split(new SimpleAsyncTaskExecutor())
////                .add(jobSplitFLow2())
////                .end()
////                .build();// 让两个flow分别在各自的线程中异步执行
////    }
//}
