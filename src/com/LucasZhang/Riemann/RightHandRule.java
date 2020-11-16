package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.lang.Math;
import java.util.concurrent.BlockingDeque;

public class RightHandRule extends AbstractRiemann{

    public double slice(Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            return Math.abs(poly.evaluateWith(xRight)*(xRight-xLeft));
        }
        else{
            return -1;
        }
    }

    public void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight) {
        if (xRight > xLeft){
            DrawableShape rect = DrawableShape.createRectangle(xLeft+(xRight-xLeft)/2,poly.evaluateWith(xRight)/2,xRight-xLeft, Math.abs(poly.evaluateWith(xRight)));

            Color color = new Color(73, 156, 84, 150);
            rect.color = color;
            rect.edgeColor = Color.BLACK;

            pframe.addDrawable(rect);
        }
    }

    public void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals){

        RightHandRule rh = new RightHandRule();
        rh.plotgraph(pframe, poly, xLeft, xRight, subintervals);
        rh.sumplot(pframe, poly, xLeft, xRight, subintervals);
        rh.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
    }

    public void totalplot2(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals, int type){
        System.out.println("Invalid Usage");
    }
}
