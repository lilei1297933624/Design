package com.lyx.batch;

public class Person {
    private String lastName;
    private String firstName;

    public Person() {
    }

    public Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static void main(String[] args) {
        String s = "aab,rder,deda,ddada,fefr,frgt,fewed,rgg,rteg,yhyhb,hadsd,kkee,dsadsads,rfsassa";
        long l = System.currentTimeMillis();
        System.out.println(s.contains("frgt"));
        System.out.println("----"+(System.currentTimeMillis()-l));
        long end = System.currentTimeMillis();
        String[] split = s.split(",");
        for(String s1 : split){
            if(s1 == "frgt")
                break;
        }
        System.out.println(System.currentTimeMillis()-end);
    }
}
