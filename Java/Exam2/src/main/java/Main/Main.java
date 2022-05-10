package Main;

import AssemblyLines.CucumberLine;
import AssemblyLines.MixedLines;
import AssemblyLines.WatermelonLine;
import Factory.Factory;

public class Main
{
    public static void main(String[] args)
    {
        CucumberLine cl1 = new CucumberLine(100);
        cl1.produce(0.35f);

        WatermelonLine wm1 = new WatermelonLine(20);
        wm1.produce(0.5f);

        MixedLines ml1 = new MixedLines(cl1,wm1);
        ml1.produce(0.5f);

        Factory f1 = new Factory();
        f1.addLine(cl1);
        f1.addLine(wm1);
        f1.addLine(ml1);
        f1.produce(0.5f);

    }
}
