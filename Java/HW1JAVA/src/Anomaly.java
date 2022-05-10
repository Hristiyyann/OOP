import java.util.ArrayList;
import java.util.Collections;

public class Anomaly
    extends Analysis
{
    private int indexes_to_be_added;

    public Anomaly(int size_for_sub_array)
    {
        super("Anomaly");
        this.indexes_to_be_added = size_for_sub_array - 1;
    }

    @Override
    public void process(ArrayList<Float> audio)
    {
        int array_size = audio.size();
        int pushed_elements_in_sub_array = 0, anomalies = 0;
        float sum_from_sub_array = 0, median;
        ArrayList<Float> sub_array_numbers = new ArrayList<>();

        for(int i = 0; i < array_size; i++)
        {
            if(pushed_elements_in_sub_array == indexes_to_be_added + 1)
            {
                float maxNum = Collections.max(sub_array_numbers);

                for(Float num : sub_array_numbers)
                {
                    if(num != maxNum)
                    {
                        sum_from_sub_array += num;
                    }
                }

                median = sum_from_sub_array / (sub_array_numbers.size() - 1);

                if(median * 2 <= maxNum)
                {
                    anomalies++;
                }

                sub_array_numbers.clear();
                pushed_elements_in_sub_array = 0;
                sum_from_sub_array = 0;
            }

            if(i + indexes_to_be_added <= array_size - 1)
            {
                for(int j = i; j <= i + indexes_to_be_added; j++)
                {
                    sub_array_numbers.add(audio.get(j));
                    pushed_elements_in_sub_array++;
                }
            }

            else
            {
                break;
            }
        }

        output = (anomalies / (float)audio.size());
    }
}
