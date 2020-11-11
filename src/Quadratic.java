import org.dalton.polyfun.Polynomial;

import java.lang.Math;

public class Quadratic {
    private double a;
    private double b;
    private double c;

    public Quadratic (double a, double b, double c){
        if (a == 0){

        }
        else {
            this.a=a;
            this.b=b;
            this.c=c;
        }
    }

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }

    public double getY(double x) {
        return a*Math.pow(x, 2) + b*x + c;
    }
    public int getRootNumber() {
        if (Math.pow(b, 2) - 4*a*c < 0){
            return 0;
        }
        else if (Math.pow(b, 2) - 4*a*c == 0){
            return 1;
        }
        else {
            return 2;
        }
    }

    public double[] getRoots(){
        if (Math.pow(b, 2) - 4*a*c < 0){
            double[] p = null;
            return p;
        }
        else if (Math.pow(b, 2) - 4*a*c == 0){
            double[] p = {-b/2*a, -b/2*a};
            return p;
        }
        else {
            double[] p = {(-b-Math.sqrt(Math.pow(b, 2) - 4*a*c))/(2*a), (-b+Math.sqrt(Math.pow(b, 2) - 4*a*c))/(2*a)};
            return p;
        }
    }

    public double getAxis() {
        return -b/2*a;
    }

    public double getExtreme() {
        return a*Math.pow(-b/2*a, 2) + b*(-b/2*a) + c;
    }

    public boolean isMax() {
        if (a>0){
            return false;
        }
        else {
            return true;
        }
    }
}
