import java.util.ArrayList;
import java.util.Collections;

public class Max
        extends Analysis
{
    public Max()
    {
        super("Max");
    }

    @Override
    public void process(ArrayList<Float> audio)
    {
        output = Collections.max(audio);
    }
}
