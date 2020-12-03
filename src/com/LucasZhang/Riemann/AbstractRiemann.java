package com.LucasZhang.Riemann;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;


public abstract class AbstractRiemann {

    /**
     * Calculates the change in X for each subinterval over the overall interval
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     * @return A double representing the change in X for each subinterval over the overall interval
     */
    public double calculateDeltax (double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft){
            return (xRight-xLeft)/subintervals;
        }
        else {
            return -1;
        }
    }

    /**
     *
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @return
     */
    public abstract double slice(Polynomial poly, double xLeft, double xRight);

    /**
     *
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     * @return
     */
    public double sum (Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft){
            double sum=0;
            for (int i = 0; i < subintervals; i++) {
                sum = sum + slice(poly, xLeft + i*calculateDeltax(xLeft, xRight, subintervals), xLeft + (i+1)*calculateDeltax(xLeft, xRight, subintervals));
                //Adds up slices over the subinterval
            }
            return sum;
        }
        else {
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
    public abstract void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight);

    /**
     *
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public void sumplot (PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft){
            for (int i = 0; i < subintervals; i++) {
                slicePlot(pframe, poly, xLeft + i*calculateDeltax(xLeft, xRight, subintervals), xLeft + (i+1)*calculateDeltax(xLeft, xRight, subintervals));
                //Repeatedly adds slices over each subinterval
            }
        }
    }

    /**
     *
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public void accumulatePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft) {
            for (int i = 0; i < subintervals; i++) {
                DrawableShape rect = DrawableShape.createRectangle(xLeft + (i + .5) * calculateDeltax(xLeft, xRight, subintervals), .5 * sum(poly, xLeft, xLeft + (i + 1) * calculateDeltax(xLeft, xRight, subintervals), i+1), calculateDeltax(xLeft, xRight, subintervals), Math.abs(sum(poly, xLeft, xLeft + (i + 1) * calculateDeltax(xLeft, xRight, subintervals), i+1)));
                //Repeatedly creates rectangles with centers halfway along the height and subinterval

                Color color = new Color(53, 146, 196, 150);
                rect.color = color;
                rect.edgeColor = Color.BLACK;

                pframe.addDrawable(rect);
            }
        }
    }

    /**
     * Uses the Fundamental Theorem of Calculus to plot the true accumulated area
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public void accumulateIntegralPlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft) {
            double[] newcoef = new double[poly.getDegree()+2]; //Creates a new double array with a size 1 greater than the number of coefficients in poly
            newcoef[0]=0; //The antiderivative can have a constant, but when calculating the definite integral, it'll cancel out anyways, so we can just immediately set it to zero

            for (int i = 0; i < poly.getDegree()+1; i++) {
                newcoef[i+1] = (poly.getCoefficientArray()[i]/(i+1)); //Sets each additional value in the new array using the reverse power rule
            }

            Polynomial newpoly = new Polynomial(newcoef); //Uses the new array to create a new polynomial
            Trail trail = new Trail();
            trail.color = Color.RED;
            pframe.addDrawable(trail);

            for (double i = xLeft; i <= xRight; i=i+calculateDeltax(xLeft, xRight, subintervals)) {
                trail.addPoint(i, newpoly.evaluateWith(i)-newpoly.evaluateWith(xLeft)); //Calculates each point using the integral
            }
        }
    }

    /**
     * Plots a given function over given subintervals
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public void plotgraph(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        if (subintervals > 0 && xRight > xLeft) {
            Trail trail = new Trail();

            Color color = new Color(197, 83, 79, 255);
            trail.color = color;

            pframe.addDrawable(trail); // Add the trail of the function to the plot frame
            for (double i = xLeft; i <= xRight; i=i+calculateDeltax(xLeft, xRight, subintervals)) {
                trail.addPoint(i, poly.evaluateWith(i));
            }
        }
    }

    /**
     * Plots all functions using a specific rule object
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public abstract void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals);

    /**
     * Plots all the separate functions using a given rule
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     * @param type The type of rule
     */
    public abstract void totalplot2(Polynomial poly, double xLeft, double xRight, int subintervals, int type);
}
