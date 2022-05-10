import java.util.HashMap;
public abstract class Building
{
    protected String buildingName;
    protected int id;
    protected HashMap<String, Integer> materials = new HashMap<>();

    public abstract boolean isLocked(Player player);
    public abstract boolean canBuild(Player player);
    public abstract Building build(Player player);

    public Building(String buildingName, int id)
    {
        this.buildingName = buildingName;
        this.id = id;
    }
}
