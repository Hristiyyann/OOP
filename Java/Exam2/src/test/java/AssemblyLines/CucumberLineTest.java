package AssemblyLines;

import Box.Crate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CucumberLineTest {

    CucumberLine c1;

    @BeforeEach
    void setUp()
    {
        c1 = new CucumberLine(100);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void produceWith1()
    {
        ArrayList<Crate> crates = c1.produce(1f);
        assertEquals(10, crates.size());
    }

    @Test
    void produceWith035()
    {
        ArrayList<Crate> crates = c1.produce(0.35f);
        assertEquals(4, crates.size());
    }
}