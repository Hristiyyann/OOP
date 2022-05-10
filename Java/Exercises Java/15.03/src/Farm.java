import java.util.HashMap;
import java.util.Map;

public class Farm
    extends Building
{
    public Farm()
    {
        super("Farm", 1);
        HashMap<String, Integer> neededMaterials = new HashMap<>();
        neededMaterials.put("Wood", 60);
        materials = neededMaterials;
    }

    @Override
    public boolean isLocked(Player player)
    {
        return true;
    }

    @Override
    public boolean canBuild(Player player)
    {
        for(String material : materials.keySet())
        {
            if(material.equals("Wood"))
            {
                if(materials.get(material) >= 60)
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
    public Farm build(Player player)
    {
        if(canBuild(player) != false)
        {
            HashMap<String, Integer> neededMaterials = new HashMap<>();
            neededMaterials.put("Wood", 60);
            return new Farm();
        }
        return null;
    }
}
