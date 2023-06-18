package com.lyx.batch.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class  Student{
    private String className;
    private String studentName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Student(String className, String studentName) {
        this.className = className;
        this.studentName = studentName;
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("一年级二班", "小明"));
        list.add(new Student("一年级二班", "小芳"));
        list.add(new Student("一年级二班", "小华"));
        list.add(new Student("一年级三班", "翠花"));
        list.add(new Student("一年级三班", "香兰"));
        // 集合中对象属性转map
       // Map<String, String> map = list.stream().collect(Collectors.toMap(Student :: getClassName, Student :: getStudentName));

        Map<String, String> map2 = list.stream().collect(Collectors.toMap(Student :: getClassName, Student :: getStudentName,
                (key1 , key2)-> key2 ));

        System.out.println(map2);
    }

}
