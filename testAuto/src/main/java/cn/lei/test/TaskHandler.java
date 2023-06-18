package cn.lei.test;

public class TaskHandler implements TaskCallable<TaskResult> {
    @Override
    public TaskResult callable(TaskResult taskResult) {
        System.out.println(taskResult.toString());
        return taskResult;
    }
}
