package AssemblyLines;

import Box.Crate;

import java.util.ArrayList;

public class CucumberLine
    extends MainLine
{
    public CucumberLine(int capacityForProducing)
    {
        super("CucumberLine", capacityForProducing);
    }

    @Override
    public ArrayList<Crate> produce(float load)
    {
        ArrayList<Crate> crates = new ArrayList<>();
        Crate crate = new Crate("Cucumbers");
        crates.add(crate);
        double jarsForProducing = Math.floor(capacityForProducing * load);
        int maxJarsForCrate = Crate.getMaxJars();

        while(jarsForProducing > 0)
        {
            if(crate.getJarCount() < maxJarsForCrate)
            {
                crate.setJarCount(1);
            }
            else
            {
                crates.add(crate);
                crate = new Crate("Cucumbers");
            }
            jarsForProducing--;
        }

        return crates;
    }
}
