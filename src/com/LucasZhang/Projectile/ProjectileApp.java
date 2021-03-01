package com.LucasZhang.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.display.Trail;

import java.awt.*;
import java.lang.Math;

public class ProjectileApp extends AbstractSimulation {
    PlotFrame plotFrame = new PlotFrame("x", "y", "Projectile Motion");
    Circle circle = new Circle();

    double totalTime;

    double xVelocity;
    double yVelocity;

    double xchange;
    double ychange;
    double falsex;
    double falsey;

    double resCoef;

    double grav;
    double mass;
    double radius;
    double density;

    double ydrag;
    double xdrag;

    boolean fall;

    @Override
    public void reset() {
        control.setValue("Starting Y position", 100);
        control.setValue("Starting X position", 0);

        control.setValue("Starting Velocity", 10);
        control.setValue("Starting Angle", 45);

        control.setValue("Drag Coefficient", 0.02);

        control.setValue("Gravity", 9.8);
        control.setValue("Mass", 1);
        control.setValue("Radius", 1);
        control.setValue("Density", 1.225);
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
        xchange = 0;
        ychange = 0;
        falsex = 0;
        falsey = 0;

        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 2;

        plotFrame.addDrawable(circle);

        plotFrame.setSize(800, 800);
        plotFrame.setPreferredMinMax(0, 50, 0 , 150);
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);

        totalTime = 0;
        resCoef = control.getDouble("Drag Coefficient");
        grav = control.getDouble("Gravity");
        mass = control.getDouble("Mass");
        radius = control.getDouble("Radius");
        density = control.getDouble("Density");

        xdrag = 0;
        ydrag = 0;

        fall = false;
    }

    public void doStep() {
        Trail trail = new Trail();

        trail.color = new Color(44, 44, 44, 255);
        plotFrame.addDrawable(trail);

        if (circle.getY() <= 0 && fall == false){
            control.println("Total distance traveled: " + circle.getX());
            fall = true;
        }

        if (circle.getY() > 0){ //stops when it hits the ground

            trail.addPoint(circle.getX(), circle.getY()); //draw dot

            ydrag = (resCoef * density) * Math.pow(yVelocity, 2) * Math.PI * Math.pow(radius, 2)/(20 * mass);
            xdrag = (resCoef * density) * Math.pow(xVelocity, 2) * Math.PI * Math.pow(radius, 2)/(20 * mass);

            if (falsey - grav/10 <= 0){ //when the object is falling
                ychange = -(grav/10) + ydrag;

                falsey = yVelocity + ychange/2;

                yVelocity = yVelocity + ychange; //true acceleration
            }
            else{
                ychange = -(grav/10) - ydrag;

                falsey = yVelocity + ychange/2;

                yVelocity = yVelocity + ychange; //true acceleration
            }
            xchange = -xdrag;

            falsex = xVelocity + xchange/2;

            xVelocity = xVelocity + xchange; //true acceleration

            circle.setY(circle.getY() + falsey/10); //movement
            circle.setX(circle.getX() + falsex/10); //movement

            totalTime++;
            plotFrame.setMessage(totalTime/10 + " Seconds");
        }
    }

    @Override
    public void stop(){
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new ProjectileApp());
    }
}
