package AssemblyLines;

import Box.Crate;

import java.util.ArrayList;

abstract public class MainLine
{
    public MainLine(String lineName, int capacityForProducing)
    {
        this.lineName = lineName;
        this.capacityForProducing = capacityForProducing;
    }

    protected String lineName;
    protected int capacityForProducing;

    abstract public ArrayList<Crate> produce(float load);
}
