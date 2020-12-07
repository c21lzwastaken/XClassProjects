package com.LucasZhang.Riemann;

import org.dalton.polyfun.Polynomial;

import org.opensourcephysics.frames.PlotFrame;

public class RiemannApp {

    public static void main(String[] args) {
        Polynomial vx = new Polynomial(new double[]{0, 2});
        Polynomial fx = new Polynomial(new double[]{0, 0, 1});
        Polynomial hx = new Polynomial(new double[]{0, 0, 0, .1});

        RightHandRule rh = new RightHandRule();
        LeftHandRule lh = new LeftHandRule();
        ArcLength al = new ArcLength();
        PlotRiemann pr = new PlotRiemann();

        PlotFrame pf = new PlotFrame("x", "y", "Riemann App");
        pf.setSize(800, 800);
        pf.setPreferredMinMax(0, 10, 0, 100);
        pf.setDefaultCloseOperation(3);
        pf.setVisible(true);

        //rh.accumulatePlot(pf, fx, 0, 10, 20);
        //rh.sumplot(pf, fx, 0, 10, 20);
        //lh.sumplot(pf, fx, 0, 10, 20);
        //rh.plotgraph(pf, fx, 0, 10, 20);
        //rh.plotgraph(pf, hx, -10, 10, 40);
        //rh.sumplot(pf, hx, -10, 10, 40);

        //pr.totalplot2(hx, -10, 10, 20, 1);

        //al.sumplot(pf, hx, 0, 30, 60);
        //al.sumplot(pf, fx, 0, 30, 60);
        //al.totalplot(pf, hx, 0, 10, 20);

        //pr.totalplot2(pf, hx, -10, 10, 40, 1);
        //pr.totalplot2(pf, fx, 0, 10, 120, 1);
    }
}
