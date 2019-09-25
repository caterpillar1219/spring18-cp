import java.awt.*;

public class NBody {
    public static double readRadius(String PlanetsPath){
        In in = new In(PlanetsPath);
        int planetsNumber = in.readInt();
        double universeRadius = in.readDouble();
        return universeRadius;
    }

    public static Planet[] readPlanets(String PlanetsPath){
        In in = new In(PlanetsPath);
        int planetsNumber = in.readInt();
        double universeRadius = in.readDouble();
        Planet[] plist = new Planet[planetsNumber];
        for (int i = 0; i < planetsNumber; i ++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            plist[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return plist;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String background = "images/starfield.jpg";
        String filename = args[2];
        Planet[] planetsList = readPlanets(filename);
        int planetsNumber = planetsList.length;
        double universeRadius = readRadius(filename);
        StdDraw.setScale(-1.5 * universeRadius, 1.5 * universeRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, background);
        for (Planet planet : planetsList) {
            planet.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);

        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T) {
            double[] xForces = new double[planetsNumber];
            double[] yForces = new double[planetsNumber];
            for (int i = 0; i < planetsNumber; i++) {
                xForces[i] = planetsList[i].calcNetForceExertedByX(planetsList);
                yForces[i] = planetsList[i].calcNetForceExertedByY(planetsList);
            }
            for (int i = 0; i < planetsNumber; i++) {
                planetsList[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Planet planet : planetsList) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planetsNumber);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < planetsNumber; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planetsList[i].xxPos, planetsList[i].yyPos, planetsList[i].xxVel,
                    planetsList[i].yyVel, planetsList[i].mass, planetsList[i].imgFileName);
        }
    }

}
