import java.util.ArrayList;
import java.util.Collections;

public class Normalize
    extends Effect
{
    public Normalize()
    {
        super("Normalize");
    }

    @Override
    public void process(ArrayList<Float> audio) {
        float numberForAdd;
        Float minNumber = Collections.min(audio);
        Float maxNumber = Collections.max(audio);

        for (int j = 0; j < audio.size(); j++)
        {
            numberForAdd = 2 * ((audio.get(j) - minNumber) / (maxNumber - minNumber)) - 1;
            numberForAdd = (float) (Math.round(numberForAdd * 100000.0) / 100000.0);
            output.add(j, numberForAdd);
        }
    }
}
