package com.lyx.batch.steps;
//
//import com.mw.usims.fts.model.BaseModel;
//import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LiAnAn on 2015/2/3.
 */
public class Test1 {
    public static  <T> T convertClass(Object obj,Class<T> cla) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Map<String,Object> maps = new HashMap<String,Object>();
        T  dataBean = null;
        if(null==obj) {
            return null;
        }
        Class<?> cls = obj.getClass();
        dataBean = cla.newInstance();
        Field[] fields = cls.getDeclaredFields();
        Field[] beanFields = cla.getDeclaredFields();
        for(Field field:fields){
            String fieldName = field.getName();
            String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
            Method methodGet = cls.getDeclaredMethod(strGet);
            Object object = methodGet.invoke(obj);
            maps.put(fieldName,object==null?"":object);
        }

        for(Field field:beanFields){
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            String fieldValue = maps.get(fieldName)==null?null:maps.get(fieldName).toString();
            if(fieldValue!=null){
                try {
                    if(String.class.equals(fieldType)){
                        field.set(dataBean, fieldValue);
                    }else if(byte.class.equals(fieldType)){
                        field.setByte(dataBean, Byte.parseByte(fieldValue));

                    }else if(Byte.class.equals(fieldType)){
                        field.set(dataBean, Byte.valueOf(fieldValue));

                    }else if(boolean.class.equals(fieldType)){
                        field.setBoolean(dataBean, Boolean.parseBoolean(fieldValue));

                    }else if(Boolean.class.equals(fieldType)){
                        field.set(dataBean, Boolean.valueOf(fieldValue));

                    }else if(short.class.equals(fieldType)){
                        field.setShort(dataBean, Short.parseShort(fieldValue));

                    }else if(Short.class.equals(fieldType)){
                        field.set(dataBean, Short.valueOf(fieldValue));

                    }else if(int.class.equals(fieldType)){
                        field.setInt(dataBean, Integer.parseInt(fieldValue));

                    }else if(Integer.class.equals(fieldType)){
                        field.set(dataBean, Integer.valueOf(fieldValue));

                    }else if(long.class.equals(fieldType)){
                        field.setLong(dataBean, Long.parseLong(fieldValue));

                    }else if(Long.class.equals(fieldType)){
                        field.set(dataBean, Long.valueOf(fieldValue));

                    }else if(float.class.equals(fieldType)){
                        field.setFloat(dataBean, Float.parseFloat(fieldValue));

                    }else if(Float.class.equals(fieldType)){
                        field.set(dataBean, Float.valueOf(fieldValue));

                    }else if(double.class.equals(fieldType)){
                        field.setDouble(dataBean, Double.parseDouble(fieldValue));

                    }else if(Double.class.equals(fieldType)){
                        field.set(dataBean, Double.valueOf(fieldValue));

                    }else if(Date.class.equals(fieldType)){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        field.set(dataBean, sdf.parse(fieldValue));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        return dataBean;

    }

    public static void main(String arg0[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Employ employ = new Employ();
        employ.setAa(11);
        employ.setBb("aa");
        int i = employ.hashCode();
        System.out.println(i);
        Employ2 employ21 = new Employ2();
        employ21.setAa(99);
        ArrayList<Employ> employs = new ArrayList<>();
        for(int j = 0; j < 100000; j++){
            Employ employ1 = new Employ();
            employ1.setAa(j);
            employ1.setBb("aaa");
            employs.add(employ1);
        }

        employs.add(employ);
        employs.add(employ);
        System.out.println(employs.hashCode());
//        Employ2 employ2 = convertClass(employ, Employ2.class);
//        System.out.println(employ2);
        Employ t = Utils.newByCglib(employ.getClass());
        System.out.println(t);
    }


}

class Employ2{
    private int aa;
    private String bb;
    private Employ2 cc;



    @Override
    public String toString() {
        return "Employ2{" +
                "aa=" + aa +
                ", bb='" + bb + '\'' +
                '}';
    }

    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public Employ2 getCc() {
        return cc;
    }

    public void setCc(Employ2 cc) {
        this.cc = cc;
    }
}

class Employ{
    private int aa;
    private String bb;
    private Employ2 cc;


    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    @Override
    public String toString() {
        return "Employ{" +
                "aa=" + aa +
                ", bb='" + bb + '\'' +
                '}';
    }
}
