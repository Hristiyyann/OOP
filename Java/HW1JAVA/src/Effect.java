import java.util.ArrayList;

public abstract class Effect
    implements Processable
{
    protected String type_effect;
    protected ArrayList<Float> output = new ArrayList<Float>();

    public Effect(String type_effect)
    {
        this.type_effect = type_effect;
    }

    public String getType_effect()
    {
        return type_effect;
    }

    public ArrayList<Float> getOutput()
    {
        return output;
    }

    public String toString()
    {
        String for_print = "<" + type_effect + ">< >";
        return for_print;
    }

}

