package cn.lei.test;

import java.util.concurrent.*;

public class TaskCallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskCallable<TaskResult> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor =
                new TaskExecutor(taskCallable,"测试回调任务");
        new Thread(taskExecutor).start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "测试Future获取异步结果";
            }
        });
        System.out.println(future.get());
        executorService.shutdown();
    }
}
