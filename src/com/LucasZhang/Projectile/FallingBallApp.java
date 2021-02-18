package com.LucasZhang.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.display.Trail;

import java.awt.*;
import java.lang.Math;

public class FallingBallApp extends AbstractSimulation {
    PlotFrame motion = new PlotFrame("x", "y", "Projectile Motion");
    Circle circle = new Circle();

    double totalTime;

    double yVelocity;

    double resCoef;

    double grav;
    double mass;
    double radius;
    double density;

    PlotFrame velocity = new PlotFrame("Time", "Velocity", "Velocity");

    @Override
    public void reset() {
        control.setValue("Starting height", 100);

        control.setValue("Starting Velocity", 10);

        control.setValue("Drag Coefficient", 0.02);

        control.setValue("Gravity", 10);
        control.setValue("Mass", 1);
        control.setValue("Radius", 1);
        control.setValue("Density", 1.225);
    }

    @Override
    public void initialize() {
        double startingY = control.getDouble("Starting Y position");
        circle.setXY(0, startingY);

        double startingV = control.getDouble("Starting Velocity");

        yVelocity = startingV;

        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 2;

        motion.addDrawable(circle);

        motion.setSize(800, 800);
        motion.setPreferredMinMax(0, 50, 0 , 150);
        motion.setDefaultCloseOperation(3);
        motion.setVisible(true);

        velocity.setSize(400, 400);
        velocity.setPreferredMinMax(0, 100, 0 , 50);
        velocity.setDefaultCloseOperation(3);
        velocity.setVisible(true);

        totalTime = 0;
        resCoef = control.getDouble("Drag Coefficient");
        grav = control.getDouble("Gravity");
        mass = control.getDouble("Mass");
        radius = control.getDouble("Radius");
        density = control.getDouble("Density");
    }

    public void doStep() {
        Trail trail = new Trail();
        Trail vtrail = new Trail();

        trail.color = new Color(44, 44, 44, 255);
        vtrail.color = new Color(44, 44, 44, 255);

        motion.addDrawable(trail);
        velocity.addDrawable(vtrail);

        if (circle.getY() >= 0){ //stops when it hits the ground

            circle.setY(circle.getY() + yVelocity/10); //movement

            trail.addPoint(0, circle.getY()); //draw dot
            vtrail.addPoint(totalTime, yVelocity);

            yVelocity = yVelocity - (grav/10 + (resCoef * density / (2*Math.pow(mass, 2)) ) * Math.pow(yVelocity, 2)/10 * Math.PI * Math.pow(radius, 2)); //acceleration

            totalTime = totalTime + 1/10;
        }
    }

    @Override
    public void stop(){
        System.out.println(totalTime + " secs to travel");
    }

    public static void main(String[] args) {SimulationControl.createApp(new FallingBallApp());
    }
}
