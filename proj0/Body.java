/**import org.graalvm.compiler.core.common.type.ArithmeticOpTable.UnaryOp.Sqrt;*/

public class Body {
    public double xxPos = 1.0;
    public double yyPos = 2.0;
    public double xxVel = 3.0;
    public double yyVel = 4.0;
    public double mass = 5.0;
    public String imgFileName = "jupiter.gif";

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){

    }

    /**calculate the distance between object to planet */
    public double calcDistance(Body planet){
        double distance = 0.0;
        distance = Math.sqrt(Math.pow((this.xxPos - planet.xxPos), 2) + Math.pow((this.yyPos - planet.yyPos), 2));
        return distance;        
    }

    /**calculate force exerted by planet*/
    public double calcForceExertedBy(Body planet){
        double r = this.calcDistance(planet);
        double F = 6.67 * (Math.pow(10, -11)) * this.mass * planet.mass / (Math.pow(r, 2));
        return F;
    }
    
    /**calculate force in x and y direction */
    public double calcForceExertedByX(Body planet){
        double r = this.calcDistance(planet);
        double dx = planet.xxPos - this.xxPos;
        double F = calcForceExertedBy(planet);
        double Fx = F * dx / r;
        return Fx;
    }

    public double calcForceExertedByY(Body planet){
        double r = this.calcDistance(planet);
        double dy = planet.yyPos - this.yyPos;
        double F = calcForceExertedBy(planet);
        double Fy = F * dy / r;
        return Fy;
    }

    /**update latest position*/
    public void update(double dt, double Fx, double Fy){ 
        double ax = Fx / mass;
        double ay = Fy / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    /**draw planet */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
        
}