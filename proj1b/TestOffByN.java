import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void TestEqualChars() {
        assertTrue("a == f",offByN.equalChars('a','f'));
        assertFalse("z != a",offByN.equalChars('z','a'));
    }
}