package Factory;

import AssemblyLines.CucumberLine;
import AssemblyLines.WatermelonLine;
import Box.Crate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    Factory f1;
    @BeforeEach
    void setUp()
    {
        f1 = new Factory();
        f1.addLine(new CucumberLine(100));
        f1.addLine(new WatermelonLine(20));
        f1.addLine(new CucumberLine(50));
        f1.addLine(new WatermelonLine(20));
    }

    @Test
    void produce()
    {
        ArrayList<Crate> crates = f1.produce(0.55f);
        assertEquals(13,crates.size());
    }
}