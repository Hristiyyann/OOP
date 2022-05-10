public class Main
{
    public static void main(String[] args)
    {
        Zoopark zoo = new Zoopark();
        Elephant el1 = new Elephant("IOSCHO", 790, true);
        Elephant el2 = new Elephant("ElEF", 870, false);
        Cow c1 = new Cow("Cowweer", 450);
        Cow c2 = new Cow("Calf", 320);

        zoo.addAnimal(el1);
        zoo.addAnimal(el2);
        zoo.addAnimal(c1);
        zoo.addAnimal(c2);

        System.out.println(zoo.getNeededFood());
    }
}
