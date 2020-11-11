package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import java.awt.Color;

import java.lang.Math;

public class RiemannApp {

    public static void main(String[] args) {
        Polynomial vx = new Polynomial(new double[]{0, 2});
        Polynomial fx = new Polynomial(new double[]{0, 0, 1});

        RightHandRule rh = new RightHandRule();

        PlotFrame pf = new PlotFrame("x", "y", "Riemann App");
        pf.setSize(800, 800);
        pf.setPreferredMinMax(0, 10, 0, 100);
        pf.setDefaultCloseOperation(3);
        pf.setVisible(true);

        rh.accumulatePlot(pf, fx, 0, 10, 20);
        rh.sumplot(pf, fx, 0, 10, 20);
        rh.plotgraph(pf, fx, 0, 10, 20);
        //rh.accumulateIntegralPlot(pf, vx, 0, 10, 20);
    }
}
