package cn.lei.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001","zhang");
        hashMap.put("10002","li");
        hashMap.put("10003","wang");
    }

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001","zhang");
        hashMap.put("10002","li");
        hashMap.put("10003","wang");
    }

    public void destroyDataMethod(){
        System.out.println("执行: destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId){
        return hashMap.get(uId);
    }
}
