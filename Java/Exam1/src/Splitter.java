public class Splitter
    extends Element
{
    protected int out_connections;

    public Splitter(String name, int out_connections)
    {
        super(name);
        this.out_connections = out_connections;
    }

    @Override
    public float eval()
    {
        System.out.print("Value eval of " + this.name);

        if(elements_in.size() == 0)
        {
            return 0;
        }

        return elements_in.get(0).voltage / out_connections;
    }

    @Override
    public void add_connection(Element element_for_add_in, Element element_for_add_out)
    {
        if(elements_in.size() < 1)
        {
            elements_in.add(element_for_add_in);
            System.out.println("To " + this.name +  " is added " +  element_for_add_in.name);
        }

        else
        {
            System.out.println("There is already 1 in connection to " + this.name);
        }
    }
}
