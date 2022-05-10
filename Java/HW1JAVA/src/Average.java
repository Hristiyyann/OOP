import java.util.ArrayList;

public class Average
        extends Analysis
{
    public Average()
    {
        super("Average");
    }

    @Override
    public void process(ArrayList<Float> audio)
    {
        float all_amplitudes_sum = 0;

        for(Float amplitude : audio)
        {
            all_amplitudes_sum += amplitude;
        }

        output = (all_amplitudes_sum / (float)audio.size());
    }
}
