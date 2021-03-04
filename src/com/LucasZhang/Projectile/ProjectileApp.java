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

    double falsex;
    double falsey;

    double resCoef;

    double grav;
    double mass;
    double radius;
    double density;

    boolean fall;

    double time;

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

        control.setValue("Time Increment", .1);

        control.setValue("X Maximum", 150);
        control.setValue("X Minimum", 0);
        control.setValue("Y Maximum", 50);
        control.setValue("Y Minimum", 0);
    }

    @Override
    public void initialize() {
        double startingY = control.getDouble("Starting Y position");
        double startingX = control.getDouble("Starting X position");
        circle.setXY(startingX, startingY);

        double startingV = control.getDouble("Starting Velocity");
        double startingA = control.getDouble("Starting Angle");
        double newA = Math.toRadians(startingA);

        time = control.getDouble("Time Increment");

        xVelocity = startingV * Math.cos(newA);
        yVelocity = startingV * Math.sin(newA);

        falsex = xVelocity;
        falsey = yVelocity;

        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 2;

        plotFrame.addDrawable(circle);

        plotFrame.setSize(800, 800);
        plotFrame.setPreferredMinMax(control.getDouble("X Minimum"), control.getDouble("X Maximum"), control.getDouble("Y Minimum") , control.getDouble("Y Maximum"));
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);

        totalTime = 0;
        resCoef = control.getDouble("Drag Coefficient");
        grav = control.getDouble("Gravity");
        mass = control.getDouble("Mass");
        radius = control.getDouble("Radius");
        density = control.getDouble("Density");

        fall = false;
    }

    public void doStep() {
        Trail trail = new Trail();

        trail.color = new Color(44, 44, 44, 255);
        plotFrame.addDrawable(trail);

        if (circle.getY() <= 0 && !fall){
            control.println("Total distance traveled: " + circle.getX());
            fall = true;
        }

        if (circle.getY() > 0){ //stops when it hits the ground

            trail.addPoint(circle.getX(), circle.getY()); //draw dot

            double ydrag = (resCoef * density) * Math.pow(yVelocity, 2) * Math.PI * Math.pow(radius, 2)/(2 * time * mass);
            double xdrag = (resCoef * density) * Math.pow(xVelocity, 2) * Math.PI * Math.pow(radius, 2)/(2 * time * mass);

            if (falsey - grav*time <= 0){ //when the object is falling downwards
                double ychange = -(grav*time) + ydrag;
                falsey = yVelocity + ychange/2;
                yVelocity = yVelocity + ychange; //true acceleration
            }
            else{
                double ychange = -(grav*time) - ydrag;
                falsey = yVelocity + ychange/2;
                yVelocity = yVelocity + ychange; //true acceleration
            }
            double xchange = -xdrag;
            falsex = xVelocity + xchange/2;
            xVelocity = xVelocity + xchange; //true acceleration

            if (circle.getY() + falsey*time <=0){ //placing the ball directly on the x axis
                circle.setX(circle.getX() - (xVelocity/yVelocity)*(circle.getY()));
                circle.setY(0);
            }
            else {
                circle.setY(circle.getY() + falsey * time); //movement
                circle.setX(circle.getX() + falsex * time); //movement
            }


            totalTime++;
            plotFrame.setMessage(totalTime*time + " Seconds");
        }
    }

    @Override
    public void stop(){
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new ProjectileApp());
    }
}
