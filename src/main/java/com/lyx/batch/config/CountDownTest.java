package com.lyx.batch.config;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import com.lyx.batch.Person;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {
//        CountDownTest test = new CountDownTest();
//        test.execute();
       // CountDownTest.csv();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        System.out.println(df.format(new Date()));
        System.out.println('a'-'A');
        String s = "1204";

        System.out.println(s.substring(s.length()-2));
    }

    public static void csv(){
        CsvWriter writer = CsvUtil.getWriter("d:/sample-data2.csv", CharsetUtil.CHARSET_UTF_8);
        List<Person> persons = new ArrayList<>();
        for(int i = 0 ; i < 100000 ; i++){
            Person person = new Person();
            String s1 = "2"+ RandomUtil.randomStringWithoutStr(8,"0123456789");
            String s2 = "2"+RandomUtil.randomStringWithoutStr(5,"0123456789");
            person.setFirstName(s1);
            person.setLastName(s2);
            persons.add(person);
        }
        writer.writeBeans(persons);
    }

    public void execute() throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(1);
        MyThread myThread = new MyThread(latch);
        myThread.start();
        latch.await();
        System.out.println("run finish -----time-----"+(System.currentTimeMillis()-start));
    }

    public class MyThread extends Thread{
        private CountDownLatch latch;

        public MyThread(){

        }
        public MyThread(CountDownLatch latch){
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Integer i = 10 /0;
                System.out.println("执行完毕------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }

}



