package com.lyx.batch.builder;


public class Person {
    int id;
    String name;
    int age;
    double weight;
    int score;
    Location location;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", score=" + score +
                ", location=" + location +
                '}';
    }

    private Person(){

    }


    public static class PersonBuilder {
        Person p = new Person();

        public PersonBuilder basicInfo(int id,String name,int age){
            p.id = id;
            p.name = name;
            p.age = age;
            return this;
        }

        public PersonBuilder weight(double weight){
            p.weight = weight;
            return this;
        }

        public PersonBuilder score(int score){
            p.score = score;
            return this;
        }

        public PersonBuilder Loc(String provice,String roomNo){
            p.location = new Location(provice,roomNo);
            return this;
        }
    }

}


class Location{
    String provice;
    String shi;

    public Location(String provice, String shi) {
        this.provice = provice;
        this.shi = shi;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }
}
