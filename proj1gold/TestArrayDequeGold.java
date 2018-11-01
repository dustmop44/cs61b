import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void Testing() {
        int x = 0;
        int y = 0;
        String methods = "";
        StudentArrayDeque<Integer> Dequex = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> Dequey = new ArrayDequeSolution<>();
        while (x == y) {
            int z = StdRandom.uniform(4);
            if (z == 0) {
                int w = StdRandom.uniform(100);
                Dequex.addFirst(w);
                Dequey.addFirst(w);
                methods += "addFirst("+w+")";
            } else if (z == 1) {
                int w = StdRandom.uniform(100);
                Dequex.addLast(w);
                Dequey.addLast(w);
                methods += "addLast("+w+")";
            } else if (Dequex.size() == 0) {
                continue;
            } else if (z == 2) {
                x = Dequex.removeFirst();
                y = Dequey.removeFirst();
                methods += "removeFirst()";
                assertEquals(methods, x, y);
            } else if (z == 3) {
                x = Dequex.removeLast();
                y = Dequey.removeLast();
                methods += "removeLast()";
                assertEquals(methods, x, y);
            }

        }
    }
}
