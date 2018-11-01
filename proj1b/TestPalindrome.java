import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    **/
    @Test
    public void testWordToDeque() {
        Deque d = Palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. */

    @Test
    public void testIsPalindrome() {
        boolean x = Palindrome.isPalindrome("racecar");
        boolean y = Palindrome.isPalindrome("a");
        boolean z = Palindrome.isPalindrome("horse");
        assertEquals(true, x);
        assertEquals(true, y);
        assertEquals(false, z);

    }

    @Test
    public void testIsPalindromecc() {
        OffByOne test = new OffByOne();
        boolean x = Palindrome.isPalindrome("ab", test);
        boolean y = Palindrome.isPalindrome("flake", test);
        boolean z = Palindrome.isPalindrome("horse", test);
        assertEquals(true, x);
        assertEquals(true, y);
        assertEquals(false, z);
    }
}
