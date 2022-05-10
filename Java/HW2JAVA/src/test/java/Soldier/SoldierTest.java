package Soldier;

import Modifiers.AttackModifier;
import Modifiers.AttributeModifier;
import Modifiers.ProtectionModifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SoldierTest {
    Soldier soldierOne, soldierTwo, soldierThree;

    @BeforeEach
    void setUp()
    {
        soldierOne = new Soldier("Warrior", 100, 5, 10, 5, "melee");
        soldierTwo = new Soldier("Big Warrior", 120, 7, 4, 11, "ranged");
        soldierThree = new Soldier("Mid Warrior", 133, 9, 2, 8, "melee");
    }

    @AfterEach
    void tearDown()
    {
        soldierOne = null;
        soldierTwo = null;
        soldierThree = null;
    }

    @Test
    void unmodifiedStats()
    {
        assertEquals(100, soldierOne.getMaximumHP());
        assertEquals(100, soldierOne.getCurrentHP());
        assertEquals(5, soldierOne.getBaseAttackForce());
        assertEquals(10, soldierOne.getBaseMeleeProtection());
        assertEquals(5, soldierOne.getBaseRangedProtection());
    }

    @Test
    void modifiedStats() {
        soldierOne.addModifier(new AttributeModifier("maximumHP",5));
        assertEquals(105, soldierOne.getMaximumHP());

        soldierOne.addModifier(new AttributeModifier("baseAttackForce",10));
        assertEquals(15, soldierOne.getBaseAttackForce());

        soldierOne.addModifier(new ProtectionModifier("melee",null,15));
        assertEquals(25, soldierOne.getBaseMeleeProtection());

        soldierOne.addModifier(new ProtectionModifier("ranged",null,20));
        assertEquals(25, soldierOne.getBaseRangedProtection());
    }

    @Test
    void chooseTargetFrom2Soldiers()
    {
        soldierOne.addModifier(new AttackModifier("archer",3));
        soldierTwo.addTag("knight");
        soldierThree.addTag("archer");

        ArrayList<Soldier> soldiers = new ArrayList<>();
        soldiers.add(soldierTwo);
        soldiers.add(soldierThree);

        Soldier returnedSoldier = soldierOne.chooseTarget(soldiers);

        assertTrue(returnedSoldier.equals(soldierThree));
        // Тук очаква soldier3, защото на soldierOne му се добавя допълнителна атака срещу войник с таг archer, а soldierThree
        // има такъв таг.
    }

    @Test
    void getDamageAgainst()
    {
        soldierOne.addModifier(new AttackModifier("archer",3));
        soldierOne.addModifier(new AttackModifier("knight",4));

        soldierTwo.addTag("archer");

        //Тук е 8, защото soldierOne има +3 атака срещу archer
        assertEquals(8,soldierOne.getDamageAgainst(soldierTwo));
        //Тук е 5, защото solderThree няма никакви тагове
        assertEquals(5,soldierOne.getDamageAgainst(soldierThree));

        soldierThree.addTag("knight");
        //Тук вече е 9, защото soldierOne има +4 срещу knight
        assertEquals(9,soldierOne.getDamageAgainst(soldierThree));
    }

    @Test
    void getDamageFromMelee()
    {
        soldierOne.addModifier(new AttackModifier(null,7));
        soldierOne.addTag("archer");

        soldierTwo.addModifier(new ProtectionModifier("melee", null,1));
        soldierThree.addModifier(new ProtectionModifier("melee", "archer",4));

        //Тук е 7, защото атаката на soldierOne срещу soldierTwo e 12(5 база + 7 модификатор). Защитата на soldierTwo e
        // 12 - всички модификатори срещу melee(1) - базовата защита(4)
        assertEquals(7,soldierTwo.getDamageFrom(soldierOne,soldierOne.getDamageAgainst(soldierTwo), "melee"));
        //Тук е 6, защото атаката на soldierOne срещу soldierThree e 12(5 база + 7 модификатор). По-специалното тук е, че soldierThree
        // има melee защита от войник с таг archer, а soldierOne има такъв таг. 12 - 4(модификатори) -2(база);
        assertEquals(6,soldierThree.getDamageFrom(soldierOne,soldierOne.getDamageAgainst(soldierThree), "melee"));
    }

    @Test
    void getDamageFromRanged()
    {
        Soldier soldierFour = new Soldier("Giant", 97, 8, 4, 7, "ranged");
        //Тук е 3, защото атаката на soldierFour срещу soldierOne e 8. Няма никакви други модификатори, за това защитата е равна на
        // 8 - 5
        assertEquals(3,soldierOne.getDamageFrom(soldierFour,soldierFour.getDamageAgainst(soldierOne),"ranged"));

        soldierFour.addModifier(new AttackModifier(null,6));
        soldierFour.addTag("archer");

        soldierOne.addModifier(new ProtectionModifier("ranged","archer",4));
        soldierOne.addModifier(new ProtectionModifier("ranged",null,4));

        //Тук е 1, защото атаката soldierFour срещу soldierOne е 14(8 база, 6 модификатор). soldierOne има защита от ranged войници
        // +4 и от ranged войники с таг archer +4. Така защитата става 14 - 8(модификатори) - 5(база)
        assertEquals(1,soldierOne.getDamageFrom(soldierFour,soldierFour.getDamageAgainst(soldierOne),"ranged"));
    }

    @Test
    void getDamageFromMeleeUnderZero()
    {
        //Получава се 1, защото атаката на soldierThree срещу soldierOne e 9, а защитата на soldierOne от войник с атака melee e 10
        //9 - 10 е -1, но това не е валидно и за това става 1
        assertEquals(1,soldierOne.getDamageFrom(soldierThree,soldierThree.getDamageAgainst(soldierOne), "melee"));
    }

    @Test
    void getDamageFromRangedUnderZero()
    {
        Soldier soldierFour = new Soldier("Giant", 97, 3, 4, 7, "ranged");
        //Тук също е 1, защото атаката(3) - защитата срещу ranged(5) е -2. Това е невалидно и трябва да бъде 1
        assertEquals(1,soldierOne.getDamageFrom(soldierFour,soldierFour.getDamageAgainst(soldierOne), "ranged"));
    }

    @Test
    void receiveAttack()
    {
        soldierThree.addModifier(new AttackModifier(null,6));
        soldierOne.receiveAttack(soldierThree,soldierThree.getDamageAgainst(soldierOne),"melee");
        //Тук е 95, защото атаката е 15(9 база + 6 модификатори) - 10(защита)
        assertEquals(95,soldierOne.getCurrentHP());
    }

    @Test
    void receiveAttackBiggerThanYourCurrHP()
    {
        soldierThree.addModifier(new AttackModifier(null,110));
        soldierOne.receiveAttack(soldierThree,soldierThree.getDamageAgainst(soldierOne),"melee");
        //Тук е 1, защото атаката е 119(9 база + 110 модификатори) - 10(защита), което означава че soldierOne ще понесе
        //109 атака, а живота му е 100. -9 е невалидно, за това става 1
        assertEquals(1,soldierOne.getCurrentHP());
    }

    @Test
    void testToString()
    {
        soldierTwo.addModifier(new AttackModifier(null,4));
        soldierTwo.addModifier(new AttackModifier("witch",5));
        soldierTwo.addModifier(new ProtectionModifier("ranged",null,3));

        String expected = "Big Warrior\n" +
                "\tHP: 120/120\n" +
                "\tATK: 7/4\n" +
                "\tDEF: 4+0/11+3\n" +
                "Modifiers:\n" +
                "Extra attack against \"witch\" for 5\n";
        
        assertEquals(expected, soldierTwo.toString());
    }
}