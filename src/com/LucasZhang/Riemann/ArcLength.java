package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.lang.Math;

public class ArcLength extends AbstractRiemann{

    /**
     * Calculates the arc length of a slice
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @return
     */
    public double slice(Polynomial poly, double xLeft, double xRight){
        if(xRight>xLeft){
            return Math.sqrt( Math.pow( Math.abs( poly.evaluateWith(xRight)-poly.evaluateWith(xLeft) ), 2 ) + Math.pow( (xRight-xLeft), 2 ) );
        }
        else{
            return -1;
        }
    }

    /**
     * Draws the value of the arc length into a rectangle
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     */
    public void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight){
        if (xRight > xLeft){
            DrawableShape rect = DrawableShape.createRectangle(xLeft+(xRight-xLeft)/2,slice(poly, xLeft, xRight)/2,xRight-xLeft, slice(poly, xLeft, xRight));
            //Creates a rectangle based on a slice

            Color color = new Color(73, 156, 84, 150);
            rect.color = color;
            rect.edgeColor = Color.BLACK;

            pframe.addDrawable(rect);
        }
    }

    /**
     * Plots the arc length slices, the total sum of the arc lengths, and the function
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     */
    public void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        ArcLength al = new ArcLength();
        al.sumplot(pframe, poly, xLeft, xRight, subintervals);
        al.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
        al.plotgraph(pframe, poly, xLeft, xRight, 5*subintervals);

        System.out.println("The total Arc Length is: " + al.sum(poly, xLeft, xRight, subintervals));
    }

    public void totalplot2(Polynomial poly, double xLeft, double xRight, int subintervals, int type){
        System.out.println("Invalid Usage");
    }
}
