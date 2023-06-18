package cn.lei.test;

public interface TaskCallable<T> {
    T callable(T t);
}
