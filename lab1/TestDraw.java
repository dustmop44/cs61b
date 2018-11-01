public class TestDraw {
  public static void main(String[] args) {
    Double uniradius = 1000
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(uniradius, -uniradius);
    StdDraw.picture(0, 0, "images/starfield.jpg", uniradius*2, uniradius*2);
    StdDraw.show();
  }
}
