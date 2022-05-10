package AssemblyLines;

import Box.Crate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WatermelonLineTest {

    WatermelonLine wm1;
    @BeforeEach
    void setUp()
    {
        wm1 = new WatermelonLine(20);
    }

    @Test
    void produce()
    {
        ArrayList<Crate> crates = wm1.produce(0.5f);
        wm1.produce(0.5f);
        wm1.produce(0.5f);
        assertEquals(1,crates.size());
        wm1.produce(0.5f);
        wm1.produce(0.5f);
        assertEquals(2,crates.size());

    }
}