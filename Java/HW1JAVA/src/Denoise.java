import java.util.ArrayList;

public class Denoise
    extends Effect
{
    private int indexes_to_be_added;
    private int M_times;

    public Denoise(int size_for_sub_array, int M_times)
    {
        super("Denoise");
        this.indexes_to_be_added = size_for_sub_array - 1;
        this.M_times = M_times;
    }

    public String toString()
    {
        String for_print = "<" + type_effect + "><Size for subarray " + (indexes_to_be_added + 1) + ", Mtimes " + M_times + ">";
        return for_print;
    }

    @Override
    public void process(ArrayList<Float> audio)
    {
        output.addAll(audio);
        int j;
        int array_size = audio.size();
        float first_max = Integer.MIN_VALUE, second_max = Integer.MIN_VALUE;
        int index_first_max = 0, index_second_max = 0;
        int pushed_elements = 0;
        float sum_from_sub_array = 0, median;
        boolean is_max_finded = false;

        for(int i = 0; i < array_size; i++)
        {
            if(i + indexes_to_be_added <= array_size - 1)
            {
                for(j = i; j <= i + indexes_to_be_added; j++)
                {
                    if (output.get(j) > first_max)
                    {
                        second_max = first_max;
                        index_second_max = index_first_max;
                        first_max = output.get(j);
                        index_first_max = j;

                    }

                    else if (output.get(j) > second_max)
                    {
                        second_max = output.get(j);
                        index_second_max = j;
                    }
                }

                for(j = i; j <= i + indexes_to_be_added; j++)
                {
                    if(output.get(j) != first_max || is_max_finded)
                    {
                        sum_from_sub_array += output.get(j);
                        pushed_elements++;
                    }
                    else
                    {
                        is_max_finded = true;
                    }
                }

                median = sum_from_sub_array / pushed_elements;

                if(median * M_times <= output.get(index_first_max))
                {
                    output.set(index_first_max, second_max);
                }

                first_max = Integer.MIN_VALUE;
                second_max = Integer.MIN_VALUE;
                pushed_elements = 0;
                sum_from_sub_array = 0;
                is_max_finded = false;
            }


            else
            {
                break;
            }
        }
    }
}
