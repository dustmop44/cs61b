package byog.lab5;
import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random RANDOM = new Random(15);
        for (int x = 0; x < 10; x++) {
            int f = RANDOM.nextInt(15);
            System.out.println(f);
        }
    }
}
