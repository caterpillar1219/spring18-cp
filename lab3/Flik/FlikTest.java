import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        boolean x = Flik.isSameNumber(127, 127);
        assertTrue(x);
    }
}
