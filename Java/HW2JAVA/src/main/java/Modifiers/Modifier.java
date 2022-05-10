package Modifiers;

abstract public class Modifier
{
    protected final String purpose;
    protected final int valueChange;

    public Modifier(String purpose, int valueChange)
    {
        this.purpose = purpose;
        this.valueChange = valueChange;
    }

    public String getPurpose()
    {
        return purpose;
    }
    public int getValueChange()
    {
        return valueChange;
    }
}
