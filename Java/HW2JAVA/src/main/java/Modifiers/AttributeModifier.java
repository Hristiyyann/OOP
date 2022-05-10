package Modifiers;

import Soldier.Soldier;

public class AttributeModifier
    extends Modifier
    implements IModifier
{
    public AttributeModifier(String purpose, int valueChange) {
        super(purpose, valueChange);
    }

    @Override
    public int applyModifier(Soldier soldier, Soldier enemy)
    {
        if(purpose.equals("currentHP")){soldier.setCurrentHP(soldier.getCurrentHP() + valueChange);}
        else if(purpose.equals("maximumHP")){soldier.setMaximumHP(soldier.getMaximumHP() + valueChange);}
        else if (purpose.equals("baseAttackForce")) {soldier.setBaseAttackForce(soldier.getBaseAttackForce() + valueChange);}
        else if (purpose.equals("baseMeleeProtection")) {soldier.setBaseMeleeProtection(soldier.getBaseMeleeProtection() + valueChange);}
        else if (purpose.equals("baseRangedProtection")) {soldier.setBaseRangedProtection(soldier.getBaseRangedProtection() + valueChange);}
        return 0;
    }

    @Override
    public String description()
    {
        return null;
    }
}
