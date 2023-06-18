package com.lyx.batch.steps;

import com.lyx.batch.config.UclThreadConfig;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.*;

public class step7 implements Tasklet {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 0 ; i < 10 ; i++){
            testThread testThread = new testThread("线程" + i,countDownLatch);
           // executor.execute(testThread);
            Future<?> submit = executor.submit(testThread);
            System.out.println("step7接受结果"+submit.get());
        }
        countDownLatch.await();
        System.out.println("--------step7---------执行完毕");
        return RepeatStatus.FINISHED;
    }

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            200,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    static class testThread implements Runnable{
        private String name ;
        private CountDownLatch countDownLatch;
        public testThread(String name){
            this.name = name;
        }

        public testThread(String name,CountDownLatch countDownLatch){
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                //int i = 1 /0;
                System.out.println(name + "  "+Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                countDownLatch.countDown();
            }

        }
    }
}
