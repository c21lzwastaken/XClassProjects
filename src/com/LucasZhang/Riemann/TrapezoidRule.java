package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class TrapezoidRule extends AbstractRiemann{

    /**
     *
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @return
     */
    public double slice(Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            return (poly.evaluateWith(xLeft)+poly.evaluateWith(xRight))*(xRight-xLeft)/2; //Calculates the area of a slice
        }
        else{
            return -1;
        }
    }

    /**
     *
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            Trail trail1 = new Trail();

            Color color = new Color(197, 83, 79, 255);
            trail1.color = color;

            pframe.addDrawable(trail1); // add the trail to the plot frame
            trail1.addPoint(xLeft, poly.evaluateWith(xLeft));
            trail1.addPoint(xRight, poly.evaluateWith(xRight));
            //Closes off the top by drawing the polynomial

            Trail trail2 = new Trail();

            trail2.color = Color.BLACK;

            pframe.addDrawable(trail2); // add the trail to the plot frame
            //The trail is a open, three-sided shape with points at each corner of the trapezoid and an open top
            trail2.addPoint(xLeft, poly.evaluateWith(xLeft));
            trail2.addPoint(xLeft, 0);
            trail2.addPoint(xRight, 0);
            trail2.addPoint(xRight, poly.evaluateWith(xRight));
        }
    }

    /**
     * Plots the Riemann slices, the approximated accumulation function, the integral, and the actual function
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){

        TrapezoidRule tr = new TrapezoidRule();
        tr.sumplot(pframe, poly, xLeft, xRight, subintervals);
        tr.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
        tr.accumulateIntegralPlot(pframe, poly, xLeft, xRight, subintervals);
        tr.plotgraph(pframe, poly, xLeft, xRight, subintervals);

        System.out.println("The area by Trapezoid Rule is: " + tr.sum(poly, xLeft, xRight, subintervals));
    }

    public void totalplot2(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals, int type){
        System.out.println("Invalid Usage");
    }
}
