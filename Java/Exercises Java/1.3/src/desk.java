import java.util.ArrayList;

public class desk extends product implements IPrice
{
    public desk(String name, ArrayList<material> materials)
    {
        super(name, materials);
    }

    public float getPrice()
    {
        int total = 0;
        for(material m: materials)
        {
            total += (m.getPrice_per_one() * m.getQuantity() * 1.5);
        }
        return total;
    }
}
