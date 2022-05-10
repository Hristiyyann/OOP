public class Cow
    extends Animal
{

    public Cow(String name, float weigh)
    {
        super(name, weigh);
    }

    @Override
    public float getNeededFood()
    {
        return weigh * 10;
    }
}
