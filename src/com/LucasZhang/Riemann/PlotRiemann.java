package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.*;
import java.lang.Math;

public class PlotRiemann extends AbstractRiemann{

    public double slice(Polynomial poly, double xLeft, double xRight){
        System.out.println("Invalid Usage");
        return -1;
    }

    public void slicePlot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight) {
        System.out.println("Invalid Usage");
    }

    public void totalplot(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals) {
        System.out.println("Invalid Usage");
    }

    public void totalplot2(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals, int type) {

        if (type == 1){
            RightHandRule rh = new RightHandRule();
            rh.plotgraph(pframe, poly, xLeft, xRight, subintervals);
            rh.sumplot(pframe, poly, xLeft, xRight, subintervals);
            rh.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
        }
        else if (type == 2){
            LeftHandRule lh = new LeftHandRule();
            lh.plotgraph(pframe, poly, xLeft, xRight, subintervals);
            lh.sumplot(pframe, poly, xLeft, xRight, subintervals);
            lh.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
        }
        else if (type == 3){
            TrapezoidRule tr = new TrapezoidRule();
            tr.plotgraph(pframe, poly, xLeft, xRight, subintervals);
            tr.sumplot(pframe, poly, xLeft, xRight, subintervals);
            tr.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
        }
        else {
            System.out.println("Invalid Input");
        }
    }
}
