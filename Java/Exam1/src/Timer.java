public class Timer
    extends Element
{
    protected int per_calls;
    static int calls = 0;

    public Timer(String name, int per_calls)
    {
        super(name);
        this.per_calls = per_calls;
    }

    @Override
    public float eval()
    {
        calls++;
        System.out.print("Value of " + this.name + " - ");

        if(calls == per_calls)
        {
            calls = 0;

            if(elements_in.size() == 0)
            {
                return 0;
            }
            return elements_in.get(0).voltage;
        }

        return 0;
    }

    @Override
    public void add_connection(Element element_for_add_in, Element element_for_add_out)
    {
        if(elements_in.size() < 1)
        {
            elements_in.add(element_for_add_in);
            System.out.println("To " + this.name + " is added " +  element_for_add_in.name);
        }

        if(elements_out.size() < 1)
        {
            elements_out.add(element_for_add_out);
            System.out.println("To " + this.name +" element " + element_for_add_out.name + " added");
        }

    }
}
