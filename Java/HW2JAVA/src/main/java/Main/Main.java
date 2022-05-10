package Main;

import Modifiers.AttackModifier;
import Modifiers.AttributeModifier;
import Modifiers.ProtectionModifier;
import Soldier.Soldier;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Soldier s1 = new Soldier("Warrior", 200, 15,13,14, "melee");
        Soldier s2 = new Soldier("Big Warrior", 160, 25,6,17, "melee");
        Soldier s3 = new Soldier("Mid Warrior", 340, 53,6,16, "melee");
        Soldier s4 = new Soldier("Small Warrior", 170, 34,7,12, "melee");
        Soldier s5 = new Soldier("Giant", 210, 45,12,23, "melee");

        ArrayList<Soldier> soldiers  = new ArrayList<>();
        soldiers.add(s2);
        soldiers.add(s3);
        soldiers.add(s4);
        soldiers.add(s5);


        s1.addTag("witch");

        s2.addTag("archer");
        s2.addTag("knight");

        AttackModifier attackModifier = new AttackModifier("witch",5);
        AttackModifier attackModifier2 = new AttackModifier("archer",20);
        AttackModifier attackModifier3 = new AttackModifier(null,13);

        ProtectionModifier pm1 = new ProtectionModifier("melee", null, 5);
        ProtectionModifier pm2 = new ProtectionModifier("melee", "archer", 5);
        ProtectionModifier pm3 = new ProtectionModifier("ranged", "knight", 12);

        AttributeModifier at1 = new AttributeModifier("baseMeleeProtection",20);
        AttributeModifier at2 = new AttributeModifier("baseAttackForce",32);

        s1.addModifier(attackModifier); // +5 за уич
        s1.addModifier(attackModifier2); // +20 за арчър
        s1.addModifier(pm2); // + 5 за мелее арчър защита
        s1.addModifier(pm1); // +5 за мелее защита

        s2.addModifier(pm1); // +5 срешу мелее защита
        s2.addModifier(attackModifier3); // +13  за всичко
        s2.addModifier(attackModifier); // + 5 за уич

        System.out.println(s1.toString());
        s1.receiveAttack(s2, s2.getDamageAgainst(s1),s2.getAttackType());
        System.out.println(s1.toString());
        s1.addModifier(at1);
        s1.addModifier(at2);
        System.out.println(s1.toString());

        Soldier s6 = s1.chooseTarget(soldiers);
        System.out.println(s6.toString());
    }
}
