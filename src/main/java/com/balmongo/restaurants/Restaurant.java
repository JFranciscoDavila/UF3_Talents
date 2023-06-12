package com.balmongo.restaurants;

import java.awt.*;

public class Restaurant {
    private String nameRestaurant;
    private Menu menu = new Menu(new String[] {"Spaguetti","Llom","Verdura","Rap"},3.5);

    public Restaurant(String nameRestaurant, Menu menu) {
        this.nameRestaurant = nameRestaurant;
        this.menu = menu;
    }


    public double costMenu (int numDishes) {
        double cost = menu.costMenu(numDishes);
        return cost;
    }


    public String getNameRestaurant() {
        return nameRestaurant;
    }
    public Menu getMenu() {
        return menu;
    }
    @Override
    public String toString() {
        return "Restaurant [nameRestaurant=" + nameRestaurant + ", menu=" + menu + "]";
    }

}
