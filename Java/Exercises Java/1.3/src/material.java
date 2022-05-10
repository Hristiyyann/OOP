public class material
{
    private String name;
    private int quantity;
    private int price_per_one;

    public material(String name, int quantity, int price_per_one)
    {
        this.name = name;
        this.quantity = quantity;
        this.price_per_one = price_per_one;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice_per_one() {
        return price_per_one;
    }
}
