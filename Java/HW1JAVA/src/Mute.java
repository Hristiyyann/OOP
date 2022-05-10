import java.util.ArrayList;
import java.util.Collections;

public class Mute
        extends Effect
{
    public Mute()
    {
        super("Mute");
    }

    @Override
    public void process(ArrayList<Float> audio)
    {
        output.addAll(audio);
        Collections.fill(output, 0.0f);
    }
}
