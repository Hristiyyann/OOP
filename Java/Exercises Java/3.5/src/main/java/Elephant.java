public class Elephant
    extends Animal
{
    protected boolean isPregnant;

    public Elephant(String name, float weigh, boolean isPregnant)
    {
        super(name, weigh);
        this.isPregnant = isPregnant;
    }

    @Override
    public float getNeededFood()
    {
        if(isPregnant)
        {
            return 2000;
        }
        return 1000;
    }
}
