package AssemblyLines;

import Box.Crate;

import java.util.ArrayList;

public class MixedLines
    extends MainLine
{
    CucumberLine cucumberLine;
    WatermelonLine watermelonLine;

    public MixedLines(CucumberLine cucumberLine, WatermelonLine watermelonLine)
    {
        super("Concatenated",cucumberLine.capacityForProducing + watermelonLine.capacityForProducing);
        this.cucumberLine = cucumberLine;
        this.watermelonLine = watermelonLine;
    }

    @Override
    public ArrayList<Crate> produce(float load)
    {
        ArrayList<Crate> crates = cucumberLine.produce(load);
        crates.addAll(watermelonLine.produce(load));

        for(Crate crate : crates)
        {
            crate.setTurshiqType("Mixed");
        }
        return crates;
    }
}
