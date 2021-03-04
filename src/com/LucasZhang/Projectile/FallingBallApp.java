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

    double falsey;

    double grav;
    double mass;
    double radius;
    double density;
    double startingX = 0;

    double time;

    PlotFrame velocity = new PlotFrame("Time", "Velocity", "Velocity");

    @Override
    public void reset() {
        control.setValue("Starting Height", 100);

        control.setValue("Starting Velocity", 0);

        control.setValue("Drag Coefficient", 0.02);

        control.setValue("Gravity", 9.8);
        control.setValue("Mass", 1);
        control.setValue("Radius", 1);
        control.setValue("Density", 1.225);

        control.setValue("Time Increment", .1);

        control.setValue("X Maximum", -5);
        control.setValue("X Minimum", 15);
        control.setValue("Y Maximum", 100);
        control.setValue("Y Minimum", 0);
    }

    @Override
    public void initialize() {
        circle.setXY(startingX, control.getDouble("Starting Height"));
        startingX = startingX + 5; //Shifts the falling ball over by 5 every new iteration to make it easier to see

        yVelocity = control.getDouble("Starting Velocity");
        falsey = yVelocity;

        time = control.getDouble("Time Increment");

        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 2;

        motion.addDrawable(circle);

        motion.setSize(800, 800);
        motion.setPreferredMinMax(control.getDouble("X Minimum"), control.getDouble("X Maximum"), control.getDouble("Y Minimum") , control.getDouble("Y Maximum"));
        motion.setDefaultCloseOperation(3);
        motion.setVisible(true);

        velocity.setSize(400, 400);
        velocity.setPreferredMinMax(0, 10, -50 , 10);
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

        double ydrag = (resCoef * density) * Math.pow(yVelocity, 2) * Math.PI * Math.pow(radius, 2) * time/(2 * mass);

        if (circle.getY() > 0){ //stops when it hits the ground

            trail.addPoint(circle.getX(), circle.getY()); //draw dot
            vtrail.addPoint(totalTime*time, yVelocity); //draw velocity

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

            if (circle.getY() + falsey*time <=0){ //placing the ball directly on the x axis
                circle.setY(0);
            }
            else {
                circle.setY(circle.getY() + falsey * time); //movement
            }

            totalTime++;
            velocity.setMessage(totalTime*time + " Seconds");
        }
    }

    @Override
    public void stop(){
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new FallingBallApp());
    }
}
