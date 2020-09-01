public class NBody {
    /**readradius from file */
    public static double readRadius(String filename){
        In in = new In(filename);
        int num = 0;
        num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    
    /**readbodies from file */
    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] planet = new Body[5];

        for (int i = 0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planet[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planet;
    }

    public static void main(String[] args){
        if (args.length < 1){
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java Nbody T dt filname");
        }

        /**collect all needed input*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] planets = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);
        double times = 0;

        /**draw the background */
        
        StdDraw.setScale(radius*-1, radius);
        
        /**draw background */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /**draw planets */
        for (int i = 0; i < planets.length; i++){
            planets[i].draw();
        }        

        /**draw animation by buffering the images */
        StdDraw.enableDoubleBuffering();

        while(times < T){
            times += dt;
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            
            for (int i = 0; i < planets.length; i++){
                xForces[i] = 0;
                yForces[i] = 0;

                /**Calculate net xyforces into array*/
                for(int j = 0; j < planets.length; j++){
                    if (j == i){
                        continue;
                    }
                    else{
                        xForces[i] += planets[i].calcForceExertedByX(planets[j]);
                        yForces[i] += planets[i].calcForceExertedByY(planets[j]);                  
                    }
                }
                
                /**update current position after dt */
                planets[i].update(dt, xForces[i], yForces[i]);                                               
            }

            /**draw background and planets images */
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i++){
                planets[i].draw();
            }            
            StdDraw.show();            
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }
}