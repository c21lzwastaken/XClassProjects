package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.lang.Math;
import java.util.concurrent.BlockingDeque;

public class TrapezoidRule extends AbstractRiemann{

    public double slice(Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            return Math.abs((poly.evaluateWith(xLeft)+poly.evaluateWith(xRight))*(xRight-xLeft)/2);
        }
        else{
            return -1;
        }
    }

    public void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            Trail trail1 = new Trail();

            Color color = new Color(197, 83, 79, 255);
            trail1.color = color;

            pframe.addDrawable(trail1); // add the trail to the plot frame
            trail1.addPoint(xLeft, poly.evaluateWith(xLeft));
            trail1.addPoint(xRight, poly.evaluateWith(xRight));

            Trail trail2 = new Trail();

            trail2.color = Color.BLACK;

            pframe.addDrawable(trail2); // add the trail to the plot frame
            trail2.addPoint(xLeft, poly.evaluateWith(xLeft));
            trail2.addPoint(xLeft, 0);
            trail2.addPoint(xRight, 0);
            trail2.addPoint(xRight, poly.evaluateWith(xRight));
        }
    }

    public void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){

        TrapezoidRule tr = new TrapezoidRule();
        tr.plotgraph(pframe, poly, xLeft, xRight, subintervals);
        tr.sumplot(pframe, poly, xLeft, xRight, subintervals);
        tr.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
    }

    public void totalplot2(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals, int type){
        System.out.println("Invalid Usage");
    }
}
