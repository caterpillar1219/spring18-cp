public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet (double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance (Planet p){
        double dx;
        double dy;
        double r;
        dx = p.xxPos - xxPos;
        dy = p.yyPos - yyPos;
        r = Math.pow(dx * dx + dy * dy, 0.5);
        return r;
    }

    public double calcForceExertedBy (Planet p){
        return G * mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX (Planet p){
        return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY (Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX (Planet[] allPlanets){
        double netForce_x = 0;
        for (Planet p : allPlanets){
            if(this.equals(p) != true){
                netForce_x += calcForceExertedByX(p);
            }
        }
        return netForce_x;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets){
        double netForce_y = 0;
        for (Planet p : allPlanets){
            if(this.equals(p) != true){
                netForce_y += calcForceExertedByY(p);
            }
        }
        return netForce_y;
    }

    public void update (double time, double xForce, double yForce) {
        double xAcc = xForce / mass;
        double yAcc = yForce / mass;
        xxVel += xAcc * time;
        yyVel += yAcc * time;
        xxPos += xxVel * time;
        yyPos += yyVel * time;
    }

    public void draw () {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
