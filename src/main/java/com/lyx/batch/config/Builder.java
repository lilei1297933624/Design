package com.lyx.batch.config;

import java.util.ArrayList;
import java.util.List;

public class Builder {

}

class Meal {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }
    public float getCost(){
        float cost = 0.0f;
        for(Item item : items){
            cost += item.price();
        }
        return cost;
    }
    public void showItems(){
        for(Item item : items){
            System.out.println("Item : " + item.name());
            System.out.println(",Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }

}

class VegBurer extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}

class Pepsi extends ColdDrink{

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}

class Coke extends ColdDrink{

    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}

class ChickenBurger extends Burger {

    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}

abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}

interface Packing{
    public String pack();
}

interface Item{
    public String name();
    public Packing packing();
    public float price();
}

class Wrapper implements Packing {


    @Override
    public String pack() {
        return null;
    }
}

class Bottle implements Packing{

    @Override
    public String pack() {
        return "Bottle";
    }
}
