package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.lang.Math;

public class ArcLength extends AbstractRiemann{

    public double slice(Polynomial poly, double xLeft, double xRight){
        if(xRight>xLeft){
            return Math.sqrt( Math.pow( Math.abs( poly.evaluateWith(xRight)-poly.evaluateWith(xLeft) ), 2 ) + Math.pow( (xRight-xLeft), 2 ) );
        }
        else{
            return -1;
        }
    }

    public void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight){
        if (xRight > xLeft){
            DrawableShape rect = DrawableShape.createRectangle(slice(poly, xLeft, xRight)/2,poly.evaluateWith(xRight)/2,xRight-xLeft, slice(poly, xLeft, xRight));
            //Creates a rectangle based on a slice

            Color color = new Color(73, 156, 84, 150);
            rect.color = color;
            rect.edgeColor = Color.BLACK;

            pframe.addDrawable(rect);
        }
    }

    public void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){
        ArcLength al = new ArcLength();
        al.sumplot(pframe, poly, xLeft, xRight, subintervals);
        al.plotgraph(pframe, poly, xLeft, xRight, 5*subintervals);
        al.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);

        System.out.println("The total Arc Length is: " + al.sum(poly, xLeft, xRight, subintervals));
    }

    public void totalplot2(Polynomial poly, double xLeft, double xRight, int subintervals, int type){
        System.out.println("Invalid Usage");
    }
}
