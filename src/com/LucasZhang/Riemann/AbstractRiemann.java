package com.LucasZhang.Riemann;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;


public abstract class AbstractRiemann {

    public double calculateDeltax (double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft){
            return (xRight-xLeft)/subintervals;
        }
        else {
            return -1;
        }
    }

    public abstract double slice(Polynomial poly, double xLeft, double xRight);

    public double sum (Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft){
            double sum=0;
            for (int i = 0; i < subintervals; i++) {
                sum = sum + slice(poly, xLeft + i*calculateDeltax(xLeft, xRight, subintervals), xLeft + (i+1)*calculateDeltax(xLeft, xRight, subintervals));
            }
            return sum;
        }
        else {
            return -1;
        }
    }

    public abstract void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight);

    public void sumplot (PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft){
            for (int i = 0; i < subintervals; i++) {
                slicePlot(pframe, poly, xLeft + i*calculateDeltax(xLeft, xRight, subintervals), xLeft + (i+1)*calculateDeltax(xLeft, xRight, subintervals));
            }
        }
    }

    public void accumulatePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft) {
            for (int i = 0; i < subintervals; i++) {
                DrawableShape rect = DrawableShape.createRectangle(xLeft + (i + .5) * calculateDeltax(xLeft, xRight, subintervals), .5 * sum(poly, xLeft, xLeft + (i + 1) * calculateDeltax(xLeft, xRight, subintervals), i+1), calculateDeltax(xLeft, xRight, subintervals), sum(poly, xLeft, xLeft + (i + 1) * calculateDeltax(xLeft, xRight, subintervals), i+1));

                Color color = new Color(53, 146, 196, 125);
                rect.color = color;
                rect.edgeColor = Color.BLACK;

                pframe.addDrawable(rect);
            }
        }
    }
    /*
    public void accumulateIntegralPlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft) {
            double[] newcoef = new double[poly.getDegree()+1];
            for (int i = 0; i <= poly.getDegree()+1; i++) {
                newcoef[i+1] = (1/(i+1))*poly.getCoefficientArray()[i];
            }
            Polynomial newpoly = new Polynomial(newcoef);
            Trail trail = new Trail();
            trail.color = Color.RED;
            pframe.addDrawable(trail);
            for (double i = xLeft; i <= xRight; i=i+calculateDeltax(xLeft, xRight, subintervals)) {
                trail.addPoint(i, newpoly.evaluateWith(i));
            }
        }
    }

     */

    public void plotgraph(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft) {
            Trail trail = new Trail();

            Color color = new Color(197, 83, 79, 255);
            trail.color = color;

            pframe.addDrawable(trail); // add the trail to the plot frame
            for (double i = xLeft; i <= xRight; i=i+calculateDeltax(xLeft, xRight, subintervals)) {
                trail.addPoint(i, poly.evaluateWith(i));
            }
        }
    }
}
