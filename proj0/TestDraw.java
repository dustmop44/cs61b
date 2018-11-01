public class TestDraw {
  public static void main(String[] args) {
    Double uniradius = 100.0;
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(uniradius, -uniradius);
    StdDraw.picture(0, 0, "images/starfield.jpg", uniradius, uniradius);
    StdDraw.show();
  }
}
