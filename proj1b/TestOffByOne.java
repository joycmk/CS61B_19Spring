import org.junit.Test;
import static org.junit.Assert.*;
import deque.*;


public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void TestEqualChars() {
        assertTrue("a == b",offByOne.equalChars('a','b'));
        assertTrue("% == &",offByOne.equalChars('%','&'));
        assertTrue("A == B",offByOne.equalChars('A','B'));
        assertFalse("A != a",offByOne.equalChars('A','a'));
    }
}
//Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/