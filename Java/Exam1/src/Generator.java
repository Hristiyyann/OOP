public class Generator
    extends Element
{
    public Generator(String name, float voltage)
    {
        super(name);
        this.voltage = voltage;
    }

    @Override
    public float eval()
    {
        return voltage;
    }

    @Override
    public void add_connection(Element element_for_add_in, Element element_for_add_out)
    {
        if(elements_out.size() < 1)
        {
            elements_out.add(element_for_add_out);
            System.out.println("To " + this.name +" element " + element_for_add_out.name + " added");
        }

        else
        {
            System.out.println("There is already 1 out connection to " + this.name);
        }
    }
}
