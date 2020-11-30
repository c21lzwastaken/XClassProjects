package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.frames.PlotFrame;

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

    /**
     * Plots the Riemann slices, the approximated accumulation function, the integral, and the actual function based on a given Riemann rule
     * @param pframe
     * @param poly The polynomial
     * @param xLeft Leftmost point
     * @param xRight Rightmost point
     * @param subintervals Number of subintervals
     * @param type The type of rule
     */
    public void totalplot2(PlotFrame pframe, Polynomial poly, double xLeft, double xRight, int subintervals, int type) {
        //Each type between one and three maps to a particular Riemann Sum method

        if (type == 1){
            RightHandRule rh = new RightHandRule();
            rh.sumplot(pframe, poly, xLeft, xRight, subintervals);
            rh.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
            rh.accumulateIntegralPlot(pframe, poly, xLeft, xRight, subintervals);
            rh.plotgraph(pframe, poly, xLeft, xRight, subintervals);

            System.out.println("The area by Right Hand Rule is: " + rh.sum(poly, xLeft, xRight, subintervals));
        }

        else if (type == 2){
            LeftHandRule lh = new LeftHandRule();
            lh.sumplot(pframe, poly, xLeft, xRight, subintervals);
            lh.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
            lh.accumulateIntegralPlot(pframe, poly, xLeft, xRight, subintervals);
            lh.plotgraph(pframe, poly, xLeft, xRight, subintervals);

            System.out.println("The area by Left Hand Rule is: " + lh.sum(poly, xLeft, xRight, subintervals));
        }

        else if (type == 3){
            TrapezoidRule tr = new TrapezoidRule();
            tr.sumplot(pframe, poly, xLeft, xRight, subintervals);
            tr.accumulatePlot(pframe, poly, xLeft, xRight, subintervals);
            tr.accumulateIntegralPlot(pframe, poly, xLeft, xRight, subintervals);
            tr.plotgraph(pframe, poly, xLeft, xRight, subintervals);

            System.out.println("The area by Trapezoid Rule is: " + tr.sum(poly, xLeft, xRight, subintervals));
        }

        else {
            System.out.println("Invalid Input");
        }
    }
}
