import java.util.HashMap;

public class Stone
    extends Building
{
    public Stone()
    {
        super("Stone", 2);
        HashMap<String, Integer> neededMaterials = new HashMap<>();
        neededMaterials.put("Wood", 100);
        materials = neededMaterials;
    }

    @Override
    public boolean isLocked(Player player)
    {
        return true;
    }

    @Override
    public boolean canBuild(Player player) {
        for(String material : materials.keySet())
        {
            if(material.equals("Wood"))
            {
                if(materials.get(material) >= 100)
                {
                    boolean lock = isLocked(player);
                    return true && lock;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public Building build(Player player) {
        return null;
    }

    public Stone(String buildingName, int id) {
        super(buildingName, id);
    }
}
