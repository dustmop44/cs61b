import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestOffByN {

    @Test
    public void TestequalChars() {
        OffByN test = new OffByN(3);
        boolean x = test.equalChars('a', 'b');
        boolean y = test.equalChars('a', 'd');
        boolean z = test.equalChars('a', 'e');
        assertEquals(x, false);
        assertEquals(y, true);
        assertEquals(z, false);

    }
}
