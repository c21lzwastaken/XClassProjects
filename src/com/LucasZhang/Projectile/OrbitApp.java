package com.LucasZhang.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.display.Trail;

import java.awt.*;
import java.lang.Math;

public class OrbitApp extends AbstractSimulation {

    @Override
    public void reset() {

    }

    @Override
    public void initialize() {

    }

    public void doStep() {

    }

    @Override
    public void stop(){

    }
    public static void main(String[] args) {
        SimulationControl.createApp(new OrbitApp());
    }
}
