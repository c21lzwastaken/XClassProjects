package com.LucasZhang.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.display.Trail;

import java.awt.*;
import java.lang.Math;

public class FenwayParkApp extends AbstractSimulation {
    PlotFrame plotFrame = new PlotFrame("x", "y", "Baseball Motion");
    Circle circle = new Circle();

    double totalTime;

    double xVelocity;
    double yVelocity;

    double resCoef;
    double resDeg;

    double grav;

    @Override
    public void reset() {
        control.setValue("Starting Y position", 100);
        control.setValue("Starting X position", 0);

        control.setValue("Starting Velocity", 10);
        control.setValue("Starting Angle", 45);

        control.setValue("Air Resistance Coefficient", 0);
        control.setValue("Air Resistance Degree", 0);

        control.setValue("Gravity", 10);
    }

    @Override
    public void initialize() {
        double startingY = control.getDouble("Starting Y position");
        double startingX = control.getDouble("Starting X position");
        circle.setXY(startingX, startingY);

        double startingV = control.getDouble("Starting Velocity");
        double startingA = control.getDouble("Starting Angle");
        double newA = Math.toRadians(startingA);

        xVelocity = startingV * Math.cos(newA);
        yVelocity = startingV * Math.sin(newA);


        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 2;

        plotFrame.addDrawable(circle);

        plotFrame.setSize(800, 800);
        plotFrame.setPreferredMinMax(0, 50, 0 , 150);
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);

        totalTime = 0;
        resCoef = control.getDouble("Air Resistance Coefficient");
        resDeg = control.getDouble("Air Resistance Degree");
        grav = control.getDouble("Gravity");
    }

    public void doStep() {
        Trail trail = new Trail();

        trail.color = new Color(44, 44, 44, 255);
        plotFrame.addDrawable(trail);

        if (circle.getY() >= 0){ //stops when it hits the ground

            circle.setY(circle.getY() + yVelocity/10); //movement
            circle.setX(circle.getX() + xVelocity/10); //movement

            trail.addPoint(circle.getX(), circle.getY()); //draw dot

            yVelocity = yVelocity - (grav/10 + (resCoef/10) * Math.pow(yVelocity, resDeg)); //acceleration
            xVelocity = xVelocity - (resCoef/10) * Math.pow(xVelocity, resDeg); //acceleration

            totalTime++;
        }
    }

    @Override
    public void stop(){
        System.out.println(totalTime/10 + " secs to travel");
        System.out.println(circle.getX() + " units traveled");
    }

    public static void main(String[] args) {SimulationControl.createApp(new FenwayParkApp());
    }
}
