package com.lyx.batch.bridge.v2;

public class GG {
    public void chase(MM mm){
        Gift g = new WarmGift(new Flower());
        give(mm,g);
    }

    public void give(MM mm , Gift g) {
        System.out.println(g + "gived!");
    }
}


class MM {

}
