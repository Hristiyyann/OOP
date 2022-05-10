import java.util.ArrayList;

public abstract class product {
    protected String name;
    protected ArrayList<material>materials;

    public product(String name, ArrayList<material> materials)
    {
        this.name = name;
        this.materials = materials;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<material> getMaterials()
    {
        return materials;
    }
}
