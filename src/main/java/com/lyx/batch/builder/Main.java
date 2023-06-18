package com.lyx.batch.builder;

public class Main {
    public static void main(String[] args) {
        ComplexTerrainBuilder builder = new ComplexTerrainBuilder();
        Terrain build = builder.buildFort().buildMine().buildWall().build();
        System.out.println(build);

        Person.PersonBuilder personBuilder = new Person.PersonBuilder();
        Person p = personBuilder.basicInfo(1, "haha", 3).Loc("河南", "信阳").p;
        System.out.println(p);

    }
}
