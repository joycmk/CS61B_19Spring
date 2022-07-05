import java.util.ArrayList;

public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int first  = in.readInt();
        double second = in.readDouble();
        return second;
    }

    public static Body[] readBodies(String file){
        In in = new In(file);
        int length = in.readInt();
        double radius = in.readDouble();
        ArrayList<Body> bodies = new ArrayList<Body>();

        System.out.println(in.readLine());

        for(int i=0; i<length; i +=1 ) {
            double xx = in.readDouble();
            double yy = in.readDouble();

            double xv = in.readDouble();
            double yx = in.readDouble();
            double m = in.readDouble();
            String pic = in.readString();

            Body b = new Body(xx, yy, xv, yx, m, pic);
            bodies.add(b);
        }

        Body body_arr[] = new Body[bodies.size()];
        body_arr = bodies.toArray(body_arr);
        return body_arr;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        StdDraw.picture(0,0,"images/starfield.jpg");

        for(Body b:bodies){
            b.draw();
        }

        for(double time=0; time <= T; time = time + dt){
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for(int i = 0; i < bodies.length; i +=1 ){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);

            }

            for(int i = 0; i < bodies.length; i +=1 ){
                bodies[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0,0,"images/starfield.jpg");

            for(Body b:bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }


    }

}