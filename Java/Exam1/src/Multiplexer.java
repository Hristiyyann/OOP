public class Multiplexer
    extends Element
{
    private int control_ins;

    public Multiplexer(String name, int control_ins)
    {
        super(name);
        this.control_ins = control_ins;
    }

    @Override
    public float eval()
    {
        String output = "";

        for(int i = 0; i < elements_in.size(); i++)
        {
            if(elements_in.get(i).voltage > 0.5)
            {
                output += '1';
            }
            else
            {
                output += '0';
            }
        }

        int binary_to_int = Integer.parseInt(output, 2);

        //Тук остана да видя стойността на въпросия вход
        /*System.out.println(binary_to_int);

        System.out.println(elements_in.get(binary_to_int).voltage);*/

        return 0;
    }

    @Override
    public void add_connection(Element element_for_add_in, Element element_for_add_out)
    {
        if(elements_out.size() < 1)
        {
            elements_out.add(element_for_add_out);
            System.out.println("To " + this.name +  " is added " +  element_for_add_in.name);
        }

        else
        {
            System.out.println("There is already 1 out connection to " + this.name);
        }

        if(elements_in.size() < control_ins + Math.pow(2, control_ins))
        {
            elements_in.add(element_for_add_in);
            System.out.println("To " + this.name +  " is added " +  element_for_add_in.name);
        }

        else
        {
            System.out.println("There is already" + (control_ins + Math.pow(2, control_ins))+ " out connection to " + this.name);
        }
    }
}
