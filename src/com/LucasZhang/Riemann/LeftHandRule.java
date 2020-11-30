package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;

public class LeftHandRule extends AbstractRiemann{

    /**
     *
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @return
     */
    public double slice(Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            return poly.evaluateWith(xLeft)*(xRight-xLeft); //Calculates the area of a slice
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
            DrawableShape rect = DrawableShape.createRectangle(xLeft+(xRight-xLeft)/2,poly.evaluateWith(xLeft)/2,xRight-xLeft, poly.evaluateWith(xLeft));
            //Creates a rectangle based on a slice

            Color color = new Color(73, 156, 84, 125);
            rect.color = color;
            rect.edgeColor = Color.BLACK;

            pframe.addDrawable(rect);
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

        LeftHandRule lh = new LeftHandRule();
        lh.sumplot(pframe, poly, xLeft, xRight, subintervals);
        lh.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
        lh.accumulateIntegralPlot(pframe, poly, xLeft, xRight, subintervals);
        lh.plotgraph(pframe, poly, xLeft, xRight, subintervals);

        System.out.println("The area by Left Hand Rule is: " + lh.sum(poly, xLeft, xRight, subintervals));
    }

    public void totalplot2(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals, int type){
        System.out.println("Invalid Usage");
    }
}
