import java.util.ArrayList;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        //Започваме да правим поръчки
        ArrayList<String> ingredients1 = new ArrayList<>(Arrays.asList("White bread", "Chicken", "Cheese", "Iceberg", "Onion", null, "BBQ", null, null));
        Order first = new Order(ingredients1);

        ArrayList<String> ingredients2 = new ArrayList<>(Arrays.asList("Rue bread", "Beef", "Melted cheese", null, null, null, "BBQ", "Mustard", null));
        Order second = new Order(ingredients2);

        ArrayList<String> ingredients3 = new ArrayList<>(Arrays.asList("Rue bread", "Chicken", "Melted cheese", "Onion", "Pepper", "Pickles", "Mayo", null, null));
        Order third = new Order(ingredients3);

        ArrayList<String> ingredients4 = new ArrayList<>(Arrays.asList("Wheat bread", "Ham", "Cheese", "Cucumber", null, null, "Mayo", "Ketchup", null));
        Order fourth = new Order(ingredients4);

        ArrayList<String> ingredients5 = new ArrayList<>(Arrays.asList("Italian bread", "Tuna", "Cheese", "Tomato", "Pickles", "Olive", "Ranch", null, null));
        Order fifth = new Order(ingredients5);

        ArrayList<String> ingredients6 = new ArrayList<>(Arrays.asList("Grain bread", null, "Melted cheese", "Cucumber", "Onion", "Iceberg", "Mustard", "Ranch", null));
        Order sixth = new Order(ingredients6);

        ArrayList<String> ingredients7 = new ArrayList<>(Arrays.asList("White bread", "Beef", "Cheese", "Tomato", "Olive", null, null, null, null));
        Order seventh = new Order(ingredients7);
        seventh.setRequirement("no bake"); // Тук слагаме една поръчка, която да не се пече

        ArrayList<String> ingredients8 = new ArrayList<>(Arrays.asList("White bread", "Veal", "Melted cheese", "Iceberg", "Onion", null, "Ketchup", null, null));
        Order eighth = new Order(ingredients8);

        ArrayList<String> ingredients9 = new ArrayList<>(Arrays.asList("Rue bread", null, "Melted cheese", "Tomato", "Pepper", "Cucumber", "Ketchup", "Mayo", "Mustard"));
        Order ninth = new Order(ingredients9);


        Restaurant restaurant = new Restaurant();

        restaurant.addFurnace(new Furnace());
        restaurant.addFurnace(new Furnace());
        restaurant.addFurnace(new Furnace());

        restaurant.addCook(new Cook("Hristiyan"));
        restaurant.addCook(new Cook("Petur"));
        restaurant.addCook(new Cook("Ivan"));
        restaurant.addCook(new Cook("Pesho"));

        restaurant.addOrder(first);
        restaurant.addOrder(second);
        restaurant.addOrder(third);
        restaurant.addOrder(fourth);
        restaurant.addOrder(fifth);
        restaurant.addOrder(sixth);
        restaurant.addOrder(seventh);
        restaurant.addOrder(eighth);
        restaurant.addOrder(ninth);
        restaurant.addOrder(Order.longBurger("Wheat bread"));
        restaurant.addOrder(Order.veggieDelight("Rue bread"));

        Thread.sleep(7000);
        restaurant.shutdown();
        // Когато shutdown-ем готвачите ще могат да обработват само започнати поръчки. Новите от тук нататък ще се игнорират.
        restaurant.addOrder(Order.classicHam("White bread"));
        //Тук когато се опитаме да добавим нова поръчка ще се изпише, че ресторанта е затворен и не може да приеме поръчката
    }
}