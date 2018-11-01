import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    */
    @Test
    public void TestequalChars() {
        OffByOne test = new OffByOne();
        assertEquals(false, test.equalChars('a', 'a'));
        assertEquals(true, test.equalChars('b', 'a'));

    }
}
