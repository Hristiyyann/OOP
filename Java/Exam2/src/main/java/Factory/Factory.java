package Factory;

import AssemblyLines.MainLine;
import Box.Crate;

import java.util.ArrayList;

public class Factory
{
    ArrayList<MainLine> lines = new ArrayList<>();

    public ArrayList<Crate> produce(float load)
    {
        ArrayList<Crate> crates = new ArrayList<>();

        for(MainLine line : lines)
        {
            crates.addAll(line.produce(load));
        }
        return crates;
    }

    public void addLine(MainLine lineForAdd)
    {
        lines.add(lineForAdd);
    }
}
