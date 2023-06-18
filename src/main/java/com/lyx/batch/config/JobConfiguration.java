package com.lyx.batch.config;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import com.lyx.batch.Person;
import com.lyx.batch.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ThreadPoolExecutor executor;

    @Autowired
    @Qualifier("writer")
    private ItemWriter writer;

    @Autowired
    @Qualifier("reader")
    private ItemReader reader;

    public static void main(String[] args) {
        JobConfiguration.csv();
    }

    public static void csv(){
        CsvWriter writer = CsvUtil.getWriter("d:/sample-data.csv", CharsetUtil.CHARSET_UTF_8);
        List<Person> persons = new ArrayList<>();
        for(int i = 0 ; i < 200000 ; i++){
            Person person = new Person();
            String s1 = RandomUtil.randomStringWithoutStr(8,"0123456789");
            String s2 = RandomUtil.randomStringWithoutStr(5,"0123456789");
            person.setFirstName(s1);
            person.setLastName(s2);
            persons.add(person);
        }
        writer.writeBeans(persons);
    }



    @Bean
    public PersonItemProcessor processor(){
        return new PersonItemProcessor();
    }



    @Bean
    public Job importUserJob1() throws MalformedURLException {
        JobBuilder importUserJob = jobBuilderFactory.get("importUserJob1");
        Job build = importUserJob.incrementer(new RunIdIncrementer()).start(step1()).next(step2()).build();
        //Job build = importUserJob.incrementer(new RunIdIncrementer()).start(step4()).build();
        //Job build = importUserJob.incrementer(new RunIdIncrementer()).start(flow1()).split(taskExecutor())
        //        .add(flow2(),flow3(),flow4(),flow5(),flow7()).next(step3()).end()
        //        .build();

    return build;
    }

    @Bean
    public Flow flow1() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow1").start(step1()).build();
    }

    @Bean
    public Flow flow2() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow2").start(step2()).build();
    }

    @Bean
    public Flow flow3() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow3").start(step3()).build();
    }

    @Bean
    public Flow flow4() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow4").start(step5()).build();
    }
    @Bean
    public Flow flow5() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow5").start(step6()).build();
    }
    @Bean
    public Flow flow6() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow6").start(step7()).build();
    }
    @Bean
    public Flow flow7() throws MalformedURLException {
        return new  FlowBuilder<Flow>("flow7").start(step8()).build();
    }
    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("spring_batch");
        executor.setConcurrencyLimit(5);
        return executor;
    }

    @Bean
    public Step step4() throws MalformedURLException {

        return stepBuilderFactory.get("step4")
                .<Person, Person> chunk(10)
                .reader(reader)
                .writer(writer)
               .taskExecutor(taskExecutor())
                .build();
    }

//    @Bean
//    public Step step1() throws MalformedURLException {
//        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//
//                CompletableFuture<Void> t1 = CompletableFuture.supplyAsync(()->{
//                    jdbcTemplate.update("update people set first_name = CONCAT(first_name,\"1\")");
//                    return null;
//                },executor);
//
//                CompletableFuture<Void> t2 = CompletableFuture.supplyAsync(()->{
//                    jdbcTemplate.update("update people set last_name = CONCAT(last_name,\"3\")");
//                    return null;
//                },executor);
//
//                CompletableFuture<Void> all = CompletableFuture.allOf(t1,t2);
//                all.get();
//                return RepeatStatus.FINISHED;
//            }
//        }).build();
//    }

   // @Bean
    public Step step1() throws MalformedURLException {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                //jdbcTemplate.update("update people set first_name = CONCAT(first_name,\"1\")");
                Thread.sleep(3000);
                return RepeatStatus.FINISHED;
            }
        }).build();

//        return stepBuilderFactory.get("step2")
//                .<Person, Person> chunk(10)
//                .reader(reader3)
//                .writer(writer3)
//               .taskExecutor(taskExecutor())
//                .build();
    }

    //@Bean
    public Step step2() throws MalformedURLException {
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                //jdbcTemplate.update("update people set last_name = CONCAT(last_name,\"3\")");
                //int i = 1/ 0;
                Thread.sleep(4000);
                return RepeatStatus.FINISHED;
            }
        }).build();

//        return stepBuilderFactory.get("step2")
//                .<Person, Person> chunk(10)
//                .reader(reader3)
//                .writer(writer3)
//               .taskExecutor(taskExecutor())
//                .build();
    }
   // @Bean
    public Job helloWorldJob() throws MalformedURLException {
        return jobBuilderFactory.get("helloWorldJob")
                .start(step3()).next(step2())
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(2000);
                        System.out.println("hello world");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(2000);
                        System.out.println("step 5 --------------");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step6() {
        return stepBuilderFactory.get("step6")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(20000);
                        System.out.println("step 6 --------------");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step7() {
        return stepBuilderFactory.get("step7")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(2500);
                        System.out.println("step 7 --------------");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step8() {
        return stepBuilderFactory.get("step8")
                .tasklet(new Tasklet() {
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(3000);
                        System.out.println("step 8 --------------");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
