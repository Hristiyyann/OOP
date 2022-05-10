import java.util.ArrayList;

public class Zoopark
{
    protected ArrayList<Animal> animals = new ArrayList<>();
    protected float totalFood;
    public Zoopark() {}
    public void addAnimal(Animal animalForAdd)
    {
        animals.add(animalForAdd);
        totalFood += animalForAdd.getNeededFood();
    }

    float getNeededFood()
    {
        return totalFood;
    }

}
