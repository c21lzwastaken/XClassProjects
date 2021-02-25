package com.LucasZhang.Projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.display.Trail;

import java.awt.*;
import java.lang.Math;

public class FenwayParkApp extends AbstractSimulation {
    PlotFrame plotFrame = new PlotFrame("x", "y", "Baseball Motion");
    Circle circle = new Circle();
    DrawableShape wall = DrawableShape.createRectangle(95, 5.665, 1, 11.33);

    double totalTime;

    double xVelocity;
    double yVelocity;

    double resCoef;

    double grav;
    double mass;
    double radius;
    double density;

    @Override
    public void reset() {
        control.setValue("Starting Velocity", 10);
        control.setValue("Starting Angle", 45);
    }

    @Override
    public void initialize() {
        double startingY = 1;
        double startingX = 0;
        circle.setXY(startingX, startingY);

        double startingV = control.getDouble("Starting Velocity");
        double startingA = control.getDouble("Starting Angle");
        double newA = Math.toRadians(startingA);

        xVelocity = startingV * Math.cos(newA);
        yVelocity = startingV * Math.sin(newA);

        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 3;
        plotFrame.addDrawable(circle);

        wall.edgeColor = new Color(69, 152, 80, 255);
        wall.color = new Color(69, 152, 80, 255);
        plotFrame.addDrawable(wall);

        plotFrame.setSize(800, 800);
        plotFrame.setPreferredMinMax(0, 100, 0 , 50);
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);

        totalTime = 0;
        resCoef = .02;
        grav = 9.8;
        mass = .145;
        radius = .0365;
        density = 1.225;
    }

    public void doStep() {
        Trail trail = new Trail();

        trail.color = new Color(44, 44, 44, 255);
        plotFrame.addDrawable(trail);

        if (circle.getX() <= 94.5 && circle.getX() + xVelocity/10 >= 94.5 && (yVelocity/xVelocity)*(94.5-circle.getX())+circle.getY() <= 11.33){ //If the line between the two points crossses through the front edge of the wall
            circle.setY((yVelocity/xVelocity)*(94.5-circle.getX())+circle.getY());
            circle.setX(94.4);
            yVelocity = .15 * yVelocity;
            xVelocity = -.15 * Math.abs(xVelocity);
        }

        if (circle.getY() >= 0){ //stops when it hits the ground

            circle.setY(circle.getY() + yVelocity/10); //movement
            circle.setX(circle.getX() + xVelocity/10); //movement

            trail.addPoint(circle.getX(), circle.getY()); //draw dot

            if (yVelocity - grav/10 <= 0){
                yVelocity = yVelocity - (grav/10) + (resCoef * density) * Math.pow(yVelocity, 2) * Math.PI * Math.pow(radius, 2)/(20 * mass); //acceleration
            }
            else{
                yVelocity = yVelocity - (grav/10) - (resCoef * density) * Math.pow(yVelocity, 2) * Math.PI * Math.pow(radius, 2)/(20 * mass); //acceleration
            }
            if (xVelocity >= 0){
                xVelocity = xVelocity - (resCoef * density) * Math.pow(xVelocity, 2) * Math.PI * Math.pow(radius, 2)/(20 * mass); //acceleration
            }
            else{
                xVelocity = xVelocity + (resCoef * density) * Math.pow(xVelocity, 2) * Math.PI * Math.pow(radius, 2)/(20 * mass); //acceleration
            }

            totalTime++;
        }
    }

    @Override
    public void stop(){
        System.out.println(totalTime/10 + " secs to travel");
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new FenwayParkApp());
    }
}
