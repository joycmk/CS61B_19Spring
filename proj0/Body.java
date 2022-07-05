public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public final double  someNumber = 6.67e-11;

    public Body(double xP, double yP, double xV,
                      double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos,2) + Math.pow(this.yyPos - b.yyPos,2));
    }

    public double calcForceExertedBy(Body b){
        return someNumber*this.mass * b.mass / Math.pow(calcDistance(b),2);
    }

    public double calcForceExertedByX(Body b){
        return calcForceExertedBy(b) * (b.xxPos - xxPos)/calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        return calcForceExertedBy(b) * (b.yyPos - yyPos)/calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allbodys){
        double F_x = 0;
        for (Body b:allbodys){
            if (this.equals(b)){
                continue;
            }
            else{
                F_x = F_x + calcForceExertedByX(b);
            }
        }
        return F_x;
    }

    public double calcNetForceExertedByY(Body[] allbodys){
        double F_y = 0;
        for (Body b:allbodys){
            if (this.equals(b)){
                continue;
            }
            else{
                F_y = F_y + calcForceExertedByY(b);
            }
        }
        return F_y;
    }

    public void update(double dt, double fx, double fy){

        xxVel = xxVel + dt * fx/mass;
        yyVel = yyVel + dt * fy/mass;

        xxPos = xxPos + dt *xxVel;
        yyPos = yyPos + dt *yyVel;

    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);

    }
}