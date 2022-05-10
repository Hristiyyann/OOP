package AssemblyLines;

import Box.Crate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MixedLinesTest {

    MixedLines ml1;
    @BeforeEach
    void setUp()
    {
        CucumberLine cl1 = new CucumberLine(100);
        WatermelonLine wm1 = new WatermelonLine(20);
        ml1 = new MixedLines(cl1, wm1);
    }

    @Test
    void produce()
    {
        ArrayList<Crate> crates = ml1.produce(0.55f);
        assertEquals(8,crates.size());
    }
}