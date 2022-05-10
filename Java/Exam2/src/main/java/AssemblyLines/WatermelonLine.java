package AssemblyLines;

import Box.Crate;

import java.util.ArrayList;

public class WatermelonLine
    extends MainLine
{
    Double raising = 1.05;
    static int calls = 1;

    public WatermelonLine(int capacityForProducing)
    {
        super("Watermelon",capacityForProducing);
    }

    @Override
    public ArrayList<Crate> produce(float load)
    {
        ArrayList<Crate> crates = new ArrayList<>();
        Crate crate = new Crate("Watermelon jars");
        crates.add(crate);
        double jarsForProducing;
        int maxJarsForCrate = Crate.getMaxJars();

        if(calls > 1)
        {
            jarsForProducing =  Math.floor(capacityForProducing * load * raising);
            raising += Math.round(0.05f * 100) / 100.0;
            System.out.println(capacityForProducing + " * " + load + " * " + raising );
            //System.out.println(capacityForProducing * );
        }

        else
        {
            jarsForProducing = Math.floor(capacityForProducing * load);
        }

        System.out.println(raising);
        System.out.println(jarsForProducing);

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

        calls++;
        return crates;
    }
}
