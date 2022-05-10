package Modifiers;

import Soldier.Soldier;

import java.util.ArrayList;

public class AttackModifier
        extends Modifier
        implements IModifier
{
    public AttackModifier(String purpose, int valueChange)
    {
        super(purpose,valueChange);
    }

    @Override
    public int applyModifier(Soldier soldier, Soldier enemy)
    {
        ArrayList<String> enemyTags = enemy.getTags();
        for(String searchTag : enemyTags)
        {
            if(purpose.equals(searchTag))
            {
                return valueChange;
            }
        }
        return 0;
    }

    @Override
    public String description()
    {
        return "Extra attack against \"" + purpose + "\" for " + valueChange + '\n';
    }
}
