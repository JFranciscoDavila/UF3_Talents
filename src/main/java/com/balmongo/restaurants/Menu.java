package com.balmongo.restaurants;

import java.util.Arrays;


public class Menu {
    private String dishes[];
    private double unitPrize;

    public Menu(String[] dishes, double unitPrize) {
        super();
        this.dishes = dishes;
        this.unitPrize = unitPrize;
    }

    public double costMenu (int numDishes) {
        return numDishes * unitPrize;
    }
    @Override
    public String toString() {
        return "Menu [dishes=" + Arrays.toString(dishes) + ", unitPrize=" + unitPrize + "]";
    }
}
