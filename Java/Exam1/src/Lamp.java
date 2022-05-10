public class Lamp
    extends Element
{
    public Lamp(String name, float minimal_voltage)
    {
        super(name);
        this.voltage = minimal_voltage;
    }

    @Override
    public float eval()
    {
        if(elements_in.size() == 0)
        {
            System.out.println(this.name + " can't shine");
            return 0;
        }

        if (elements_in.get(0).voltage > this.voltage)
        {
            System.out.println( this.name +" is starting to shine");
            return 1;
        }

        System.out.println(this.name + " can't shine");
        return 0;
    }

    @Override
    public void add_connection(Element element_for_add_in, Element element_for_add_out)
    {
        if(elements_in.size() < 1)
        {
            elements_in.add(element_for_add_in);
            System.out.println("To "+ this.name +" is added " +  element_for_add_in.name);
        }

        else
        {
            System.out.println("There is already 1 in connection to " + this.name);
        }
    }
}
