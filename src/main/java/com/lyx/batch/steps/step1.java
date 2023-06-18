package com.lyx.batch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class step1 implements Tasklet {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private List<String> organizations = new ArrayList<>();

    public List<String> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<String> organizations) {
        this.organizations = organizations;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        //jdbcTemplate.update("update people set first_name = CONCAT(first_name,\"1\")");
        //Thread.sleep(1000);
        System.out.println("属性注入"+organizations);
        System.out.println("--------step1---------执行完毕");
        return RepeatStatus.FINISHED;
    }

    public static void main(String[] args) {
        List<String> s = Arrays.asList("aa","bb");
        System.out.println(s);
    }
}
