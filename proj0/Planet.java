import java.lang.Math;

public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public static double Gconstant = 6.67e-11;
  public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
                  xxPos = xP;
                  yyPos = yP;
                  xxVel = xV;
                  yyVel = yV;
                  mass = m;
                  imgFileName = "images/" + img;
                }
  public Planet(Planet p) {
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double dy = this.yyPos - p.yyPos;
    double dx = this.xxPos - p.xxPos;

    return Math.sqrt(dy*dy + dx*dx);
  }
  public double calcForceExertedBy(Planet p) {
    double distance = this.calcDistance(p);
    return (Planet.Gconstant * this.mass * p.mass)/(distance*distance);
  }
  public double calcForceExertedByX(Planet p) {
    double distance = this.calcDistance(p);
    double force = this.calcForceExertedBy(p);
    return force * (p.xxPos - this.xxPos) / distance;
  }
  public double calcForceExertedByY(Planet p) {
    double distance = this.calcDistance(p);
    double force = this.calcForceExertedBy(p);
    return force * (p.yyPos - this.yyPos) / distance;
  }
  public double calcNetForceExertedByX(Planet[] planets) {
    double NetForceX = 0;
    int i = 0;
    while (i < planets.length) {
      if (this.equals(planets[i])) {
        i += 1;
      } else {
        NetForceX = NetForceX + calcForceExertedByX(planets[i]);
        i += 1;
      }
    }
    return NetForceX;
  }
  public double calcNetForceExertedByY(Planet[] planets) {
    double NetForceY = 0;
    int i = 0;
    while (i < planets.length) {
      if (this.equals(planets[i])) {
        i += 1;
      } else {
        NetForceY = NetForceY + calcForceExertedByY(planets[i]);
        i += 1;
      }
    }
    return NetForceY;
  }
  public void update(double dt, double xforce, double yforce) {
    double xacc = xforce/this.mass;
    double yacc = yforce/this.mass;
    this.xxVel = this.xxVel + (dt * xacc);
    this.yyVel = this.yyVel + (dt * yacc);
    this.xxPos = this.xxPos + (dt * this.xxVel);
    this.yyPos = this.yyPos + (dt * this.yyVel);
  }
  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
  }
}
