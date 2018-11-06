package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

public class Wait {

    public static String Wait() {
        while(!StdDraw.hasNextKeyTyped()) {
        }
        return Character.toString(StdDraw.nextKeyTyped());
    }
}
