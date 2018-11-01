public class NBody {
  public static double readRadius(String s) {
    In in = new In(s);
    in.readInt();
    return in.readDouble();
  }
  public static Planet[] readPlanets(String s) {
    In in = new In(s);
    Planet[] planets = new Planet[in.readInt()];
    in.readDouble();
    int i = 0;
    while (i < planets.length) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
      i += 1;
    }
    return planets;
  }

  public static void main(String[] args) {
    Double T = Double.parseDouble(args[0]);
    Double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    Double uniradius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(uniradius, -uniradius);
    StdDraw.picture(0, 0, "images/starfield.jpg", uniradius*2, uniradius*2);
    for (int i = 0; i < planets.length; i++) {
      planets[i].draw();
    }
    StdAudio.play(audio/2001.mid);
    for (Double time = 0.0; time < T; time += dt) {
      Double[] xForces = new Double[planets.length];
      Double[] yForces = new Double[planets.length];
      for(int i = 0; i < planets.length; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for(int i = 0; i < planets.length; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.enableDoubleBuffering();
      StdDraw.setScale(uniradius, -uniradius);
      StdDraw.picture(0, 0, "images/starfield.jpg", uniradius*2, uniradius*2);
      for (int i = 0; i < planets.length; i++) {
        planets[i].draw();
      }
      StdDraw.show();
    }
    StdDraw.show();
  }

}
