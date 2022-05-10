abstract public class Animal
{
    protected String name;
    protected float weigh;

    public Animal(String name, float weigh)
    {
        this.name = name;
        this.weigh = weigh;
    }

    abstract public float getNeededFood();

    @Override
    public String toString()
    {
        return "Animal{" +
                "name='" + name + '\'' +
                ", weigh=" + weigh +
                '}';
    }
}
