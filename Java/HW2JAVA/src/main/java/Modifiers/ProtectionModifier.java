package Modifiers;

import Soldier.Soldier;

import java.util.ArrayList;

public class ProtectionModifier
    extends Modifier
    implements IModifier
{
    private String attackProtectionType;
    public ProtectionModifier(String attackProtectionType, String purpose, int valueChange)
    {
        super(purpose, valueChange);
        this.attackProtectionType = attackProtectionType;
    }

    public String getAttackProtectionType()
    {
        return attackProtectionType;
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
        return "Extra protection against \"" + purpose + "\" for " + valueChange + '\n';
    }
}
