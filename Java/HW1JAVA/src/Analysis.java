import java.util.ArrayList;

public abstract class Analysis
    implements Processable
{
    protected String type_analysis;
    protected float output = 0;

    public String toString()
    {
        return "<" + type_analysis + ">=<" + output + ">";
    }

    public Analysis(String type_analysis)
    {
        this.type_analysis = type_analysis;
    }

    public String getType_analysis()
    {
        return type_analysis;
    }

    public float getOutput()
    {
        return output;
    }
}
