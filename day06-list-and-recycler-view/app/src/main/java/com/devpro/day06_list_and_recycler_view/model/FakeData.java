package com.devpro.day06_list_and_recycler_view.model;

import com.devpro.day06_list_and_recycler_view.R;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

    public static List<Product> getCoffeeMenu() {
        List<Product> coffeeList = new ArrayList<>();

        coffeeList.add(new Product("C01", "Caramel Frappuccino", 5.50, R.drawable.caramel_frappucino, 4.8, false));

        coffeeList.add(new Product("C02", "Iced Caramel Frappuccino", 5.80, R.drawable.caramel_frappucino1, 4.7,false ));

        coffeeList.add(new Product("C03", "Classic Espresso", 3.00, R.drawable.espresso, 4.9, false));

        coffeeList.add(new Product("C04", "Double Espresso", 4.00, R.drawable.espresso1, 4.8, false));

        coffeeList.add(new Product("C05", "Classic Hot Chocolate", 4.50, R.drawable.hot_chocolate, 4.6, false));

        coffeeList.add(new Product("C06", "Hazelnut Hot Chocolate", 5.00, R.drawable.hot_chocolate1, 4.7, false));

        coffeeList.add(new Product("C07", "Traditional Ice Coffee", 3.50, R.drawable.ice_coffee, 4.4, false));

        coffeeList.add(new Product("C08", "Iced Vanilla Latte", 4.50, R.drawable.ice_coffee1, 4.6, false));

        coffeeList.add(new Product("C09", "Mixed Black Coffee", 12.00, R.drawable.mixed_black_coffee, 4.5, false));

        coffeeList.add(new Product("C10", "Premium Black Coffee", 15.00, R.drawable.mixed_black_coffee1, 4.9, false));

        return coffeeList;
    }
}