import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooparkTest {
    Zoopark zoo;
    @BeforeEach
    void setUp()
    {
        zoo = new Zoopark();
        zoo.addAnimal(new Elephant("el1", 556, true));
        zoo.addAnimal(new Cow("c1", 256));
    }

    @AfterEach
    void tearDown()
    {

    }

    @Test
    void getNeededFood()
    {
        Cow c2 = new Cow("c2", 560);
        Elephant el2 = new Elephant("ELel", 900, false);
        zoo.addAnimal(c2);
        zoo.addAnimal(el2);

        assertEquals(11160,zoo.getNeededFood());
    }

    @Test
    void getNeededFood2()
    {
        Cow c2 = new Cow("c2", 345);
        Elephant el2 = new Elephant("ELel", 864, true);
        zoo.addAnimal(c2);
        zoo.addAnimal(el2);

        assertEquals(10010,zoo.getNeededFood());
    }
}