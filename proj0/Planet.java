public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double g = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
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
        return Math.sqrt((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos) + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos));
    }

    public double calcForceExertedBy(Planet p) {

        double r = calcDistance(p);
        return g * p.mass * this.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return f * (p.xxPos - this.xxPos) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return f * (p.yyPos - this.yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet[] pArr) {
        double f = 0;
        for (Planet p : pArr) {
            if (this.equals(p)) {
                continue;
            }
            f += calcForceExertedByX(p);
        }
        return f;
    }

    public double calcNetForceExertedByY(Planet[] pArr) {
        double f = 0;
        for (Planet p : pArr) {
            if (this.equals(p)) {
                continue;
            }
            f += calcForceExertedByY(p);
        }
        return f;
    }

    public void update(double t, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += t * ax;
        this.yyVel += t * ay;
        this.xxPos += t * this.xxVel;
        this.yyPos += t * this.yyVel;
    }

    public void draw() {
        String fileName = "images/" + this.imgFileName;
        StdDraw.picture(xxPos, yyPos, fileName);
//        StdDraw.show();
//        StdDraw.pause(2000);
    }

}

