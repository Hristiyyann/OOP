package Soldier;

import Modifiers.AttackModifier;
import Modifiers.IModifier;
import Modifiers.ProtectionModifier;

import java.util.ArrayList;
import java.util.List;

public class Soldier
{
    protected String soldierName;
    protected String attackType;
    protected int currentHP, maximumHP;
    protected int baseAttackForce;
    protected int modifiedAttackForce; // Тази променлива е за модификаторите за сила, които нямат тагове
    protected int baseMeleeProtection, baseRangedProtection;
    protected int modifiedMeleeProtection, modifiedRangedProtection; // Тези променливи са за модификаторите за защита, които отново нямат посочен таг
    protected ArrayList<String> tags;
    protected ArrayList<IModifier> modifiers;

    public Soldier(String soldierName, int maximumHP, int baseAttackForce, int meleeProtection, int baseRangedProtection, String attackType)
    {
        this.soldierName = soldierName;
        this.attackType = attackType;
        this.maximumHP = maximumHP;
        this.currentHP = maximumHP;
        this.baseAttackForce = baseAttackForce;
        this.baseMeleeProtection = meleeProtection;
        this.baseRangedProtection = baseRangedProtection;
        this.tags = new ArrayList<>();
        this.modifiers = new ArrayList<>();
    }

    public void addTag(String tag)
    {
        tags.add(tag);
    }

    public void addModifier(IModifier modifierForAdd)
    {
        if(modifierForAdd.getClass() == AttackModifier.class)
        {
            if(((AttackModifier) modifierForAdd).getPurpose() == null)
            {
                modifiedAttackForce += ((AttackModifier) modifierForAdd).getValueChange();
                return;
            }
            modifiers.add(modifierForAdd);
        }
        else if (modifierForAdd.getClass() == ProtectionModifier.class)
        {
            ProtectionModifier temporary = (ProtectionModifier) modifierForAdd;
            if(temporary.getPurpose() == null)
            {
                if(temporary.getAttackProtectionType().equals("melee"))
                {
                    modifiedMeleeProtection += temporary.getValueChange();
                }
                else
                {
                    modifiedRangedProtection += temporary.getValueChange();
                }
                return;
            }
            modifiers.add(modifierForAdd);
        }
        else {modifierForAdd.applyModifier(this,null);}
    }

    public Soldier chooseTarget(List<Soldier> enemies)
    {
        int biggestHit = this.getDamageAgainst(enemies.get(0));
        int indexOfSoldier = 0;

        for(Soldier soldier : enemies)
        {
            int currentHit = this.getDamageAgainst(soldier);

            if(currentHit > biggestHit)
            {
                biggestHit = currentHit;
                indexOfSoldier = enemies.indexOf(soldier);
            }
        }

        return enemies.get(indexOfSoldier);
    }

    public int getDamageAgainst(Soldier enemy)
    {
        int forceWithAllModifiers = baseAttackForce;

        for(IModifier modifierForSearch: modifiers)
        {
            if(modifierForSearch.getClass() == AttackModifier.class)
            {
                AttackModifier temporary = ((AttackModifier) modifierForSearch);
                String tagForSearch = temporary.getPurpose();

                if(tagForSearch != null)
                {
                    forceWithAllModifiers += temporary.applyModifier(this, enemy);
                    continue;
                }
            }
        }

        return forceWithAllModifiers + modifiedAttackForce;
    }

    public int getDamageFrom(Soldier enemy, int value, String type)
    {
        int protectionWithAllModifiers = 0;
        for(IModifier modifierForSearch : modifiers)
        {
            if(modifierForSearch.getClass() == ProtectionModifier.class)
            {
                ProtectionModifier temporary = (ProtectionModifier)modifierForSearch;
                String protectionFromAttackType = temporary.getAttackProtectionType();

                if(protectionFromAttackType.equals(type))
                {
                   protectionWithAllModifiers += temporary.applyModifier(this, enemy);
                }
            }
        }
        int result;

        if(type.equals("melee"))
        {
            result = value - protectionWithAllModifiers - modifiedMeleeProtection - baseMeleeProtection;
        }
        else
        {
           result =  value - protectionWithAllModifiers - modifiedRangedProtection - baseRangedProtection;
        }

        if(result < 1) {return 1;}
        return result;
    }

    public void receiveAttack(Soldier enemy, int value, String type)
    {
        int HPToDecrease = getDamageFrom(enemy, value, type);
        if(currentHP - HPToDecrease < 0 ) {currentHP = 1; return;}
        currentHP -= HPToDecrease;
    }

    @Override
    public String toString() {
        String description = soldierName+ '\n'+
                "\tHP: " + currentHP + '/' + maximumHP + '\n' +
                "\tATK: " + baseAttackForce + '/' + (getBaseAttackForce() - baseAttackForce) + '\n' +
                "\tDEF: " + baseMeleeProtection + '+' + (getBaseMeleeProtection() - baseMeleeProtection) + "/"  +
                baseRangedProtection + "+" + (getBaseRangedProtection() - baseRangedProtection) + '\n' + "Modifiers:" + '\n';

        for(IModifier modifier : modifiers)
        {
            description += modifier.description();
        }
        return description;
    }

    public int getCurrentHP() {return currentHP;}
    public int getMaximumHP() {return maximumHP;}
    public int getBaseAttackForce() {return modifiedAttackForce + baseAttackForce;}
    public int getBaseMeleeProtection() {return baseMeleeProtection + modifiedMeleeProtection;}
    public int getBaseRangedProtection() {return baseRangedProtection + modifiedRangedProtection;}
    public String getAttackType() {return attackType;}
    public ArrayList<String> getTags() {return tags;}

    public void setCurrentHP(int currentHP) {this.currentHP = currentHP;}
    public void setMaximumHP(int maximumHP) {this.maximumHP = maximumHP;}
    public void setBaseAttackForce(int baseAttackForce) {this.baseAttackForce = baseAttackForce;}
    public void setBaseMeleeProtection(int baseMeleeProtection) {this.baseMeleeProtection = baseMeleeProtection;}
    public void setBaseRangedProtection(int baseRangedProtection) {this.baseRangedProtection = baseRangedProtection;}
}
