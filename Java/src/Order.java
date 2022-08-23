import java.util.ArrayList;
import java.util.Arrays;

public class Order
{
    private static int numberForOrder= 1;
    private int orderNumber;
    private boolean isBaked = false;
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> requirements = new ArrayList<>();

    public Order(ArrayList<String> ingredients)
    {
        this.ingredients.addAll(ingredients);
        this.orderNumber = numberForOrder;
        numberForOrder++;
    }

    public static Order classicHam(String bread)
    {
        ArrayList<String> forClassicHam = new ArrayList<>(Arrays.asList(bread, "Ham", "Cheese", "Tomato", "Onion", "Cucumber", "Mayo", null, null));
        return new Order(forClassicHam);
    }

    public static Order longBurger(String bread)
    {
        ArrayList<String> forLongBurger = new ArrayList<>
                (Arrays.asList(bread, "Veal", "Melted cheese", "Iceberg", "Pickles", null, "BBQ", null, null));
        return new Order(forLongBurger);
    }

    public static Order veggieDelight(String bread)
    {
        ArrayList<String> forClassicHam = new ArrayList<>
                (Arrays.asList(bread, null, "Cheese", "Iceberg", "Olive", "Tomato", "Ranch", null, null));
        return new Order(forClassicHam);
    }

    public boolean isValid()
    {
        for(int i = 0; i < ingredients.size(); i++)
        {
            if(i == 0 && !Restaurant.validBreads.contains(ingredients.get(i))) {return false;}
            else if(i == 1 && !(ingredients.get(i) == null || Restaurant.validMeat.contains(ingredients.get(i)))) {return false;}
            else if(i == 2 && !(ingredients.get(i).equals("Cheese") || ingredients.get(i).equals("Melted cheese"))) {return false;}
            else if(i == 3)
            {
                for(i = 3; i <= 5; i++)
                {
                    String ingredient = ingredients.get(i);
                    if(!(Restaurant.validVegetables.contains(ingredient) || (ingredient == null))) {return false;}
                    System.out.println(i);
                }
            }

            else
            {
                for(i = 6; i < 9; i++)
                {
                    String ingredient = ingredients.get(i);
                    if(!(Restaurant.validSauces.contains(ingredient) || (ingredient == null))) {return false;}
                }
            }
        }

        return true;
    }

    public void setBaked(boolean baked) {isBaked = baked;}
    public void setRequirement(String requirement) {this.requirements.add(requirement);}
    public boolean isBaked() {return isBaked;}
    public int getOrderNumber()
    {
        return orderNumber;
    }
    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    public ArrayList<String> getRequirements() {
        return requirements;
    }
}