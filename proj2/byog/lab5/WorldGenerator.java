package byog.lab5;
import byog.TileEngine.TERenderer;

import java.util.Random;

/**
 * Iterate through the hallways, cap them, then iterate through the caps and sprout them.
 * Connect random hallways that hit against walls, and make a throughway.
 * Maybe at first rooms stay static. But if then they start changing as you walk out of vision,
 * the torch will keep rooms the same. That sounds like an impossible formula to come up with.
 */

public class WorldGenerator {
    public static void Start(int seed) {
        int framewidth = 80;
        int frameheight = 45;
        TERenderer ter = new TERenderer();
        ter.initialize(framewidth, frameheight);
        Random RANDOM = new Random(seed);
        Generation world = new Generation(framewidth, frameheight, RANDOM);
        world.firstroom();
    }
    public static void Start() {
        int framewidth = 80;
        int frameheight = 45;
        TERenderer ter = new TERenderer();
        ter.initialize(framewidth, frameheight);
        Random RANDOM = new Random();
        Generation world = new Generation(framewidth, frameheight, RANDOM);
        world.firstroom();
    }
}
