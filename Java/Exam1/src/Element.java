import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Element
{
    protected String name;
    protected float voltage = 0;
    protected ArrayList<Element> elements_in = new ArrayList<>();
    protected ArrayList<Element> elements_out = new ArrayList<>();
    public abstract float eval();

    public Element(String name)
    {
        this.name = name;
    }

    public abstract void add_connection(Element element_for_add_in, Element element_for_add_out);
}
