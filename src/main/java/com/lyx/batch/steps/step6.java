package com.lyx.batch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class step6 implements Tasklet {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //jdbcTemplate.update("update people set last_name = CONCAT(last_name,\"3\")");
        Thread.sleep(6000);
        System.out.println("--------step5---------执行完毕");
        return RepeatStatus.FINISHED;
    }
}
