package Modifiers;

import Soldier.Soldier;

public interface IModifier
{
    int applyModifier(Soldier soldier, Soldier enemy);
    String description();
}
