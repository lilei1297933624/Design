package com.lyx.batch.config;

import com.lyx.batch.Person;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.util.concurrent.*;

@Configuration
public class UclThreadConfig {

    @Bean
    public FlatFileItemReader<Person> reader2() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileUrlResource("D:/sample-data2.csv"))
                .delimited()
                .names(new String[]{"firstName","lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                    setTargetType(Person.class);
                }}).build();
    }

    @Bean
    public FlatFileItemReader<Person> reader3() throws MalformedURLException {
        return null;
    }

    @Bean
    public FlatFileItemReader<Person> reader() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileUrlResource("D:/sample-data.csv"))
                .delimited()
                .names(new String[]{"firstName","lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                    setTargetType(Person.class);
                }}).build();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Person>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into people (first_name,last_name) VALUES (:firstName,:lastName)")
                .dataSource(dataSource).build();
    }


    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool){
        return new ThreadPoolExecutor(pool.getCore(),
                pool.getMaxSize(),pool.getKeepAliveTime(), TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100000),new ThreadPoolExecutor.AbortPolicy());
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
                int i = 1 /0;
                System.out.println(name + "  "+Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                countDownLatch.countDown();
            }

        }
    }

    public static void doTest(CountDownLatch countDownLatch){
        for(int i = 0 ; i < 10 ; i++){
            testThread testThread = new testThread("线程" + i,countDownLatch);
            executor.execute(testThread);
        }
    }


    public static void test1(int mill) throws InterruptedException {
        Thread.sleep(mill);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.............start.......");
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("当前运行结果:" + i);
//        }, executor);
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        long l = System.currentTimeMillis();
//        try {
//            doTest(countDownLatch);
//        }catch (Exception e){
//            System.out.println(11111111);
//            throw e;
//        }
//        countDownLatch.await();
//        System.out.println("finish"+ (System.currentTimeMillis()-l) / 1000);
//        executor.shutdown();
        try{
            long start = System.currentTimeMillis();
            CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
                //test1("aaa");
                System.out.println("查询商品的属性");
                return "黑色+256g";
            },executor);

            CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
                try {
                    test1(3000);
                    System.out.println("查询商品的图片信息");
                } catch (Exception e) {
                    return e.toString();
                }
                return "hello.jpg";
            },executor);

            CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
                try {
                    test1(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("查询商品的介绍");
                return "华为";
            },executor);
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futureAttr, futureImg, futureDesc);
            System.out.println("-------------time1:"+(System.currentTimeMillis()-start));

            allOf.get();//等待所有线程执行完

            System.out.println("-------------time2:"+(System.currentTimeMillis()-start));

            System.out.println("main.............end......."+futureAttr.get()+"=>"+futureImg.get()+"=>"+futureDesc.get() );
            System.out.println("-------------time3:"+(System.currentTimeMillis()-start));
        }catch (Exception e){
           throw e;
        }

    }


    public String test1(String name) throws Exception {
        int a = 100;
        if(a > 101){
            throw new Exception("大于100报错");
        }
        return "a";
    }
}


