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
    DrawableShape wall = DrawableShape.createRectangle(94.75, 5.665, .5, 11.33); //The wall

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

    boolean pass;
    boolean fall;

    double time;

    @Override
    public void reset() {
        control.setValue("Starting Velocity", 10);
        control.setValue("Starting Angle", 45);

        control.setValue("Time Increment", .1);

        control.setValue("X Maximum", 100);
        control.setValue("X Minimum", 0);
        control.setValue("Y Maximum", 50);
        control.setValue("Y Minimum", 0);
    }

    @Override
    public void initialize() {
        double startingY = 1; //starting position is hardcoded
        double startingX = 0;
        circle.setXY(startingX, startingY);

        time = control.getDouble("Time Increment");

        double startingV = control.getDouble("Starting Velocity");
        double startingA = control.getDouble("Starting Angle");
        double newA = Math.toRadians(startingA);

        xVelocity = startingV * Math.cos(newA);
        yVelocity = startingV * Math.sin(newA);
        falsex = xVelocity;
        falsey = yVelocity;

        circle.color = new Color(168, 105, 118, 255);
        circle.pixRadius = 3;
        plotFrame.addDrawable(circle);

        wall.edgeColor = new Color(69, 152, 80, 255);
        wall.color = new Color(69, 152, 80, 255);
        plotFrame.addDrawable(wall);

        plotFrame.setSize(800, 800);
        plotFrame.setPreferredMinMax(control.getDouble("X Minimum"), control.getDouble("X Maximum"), control.getDouble("Y Minimum") , control.getDouble("Y Maximum"));
        plotFrame.setDefaultCloseOperation(3);
        plotFrame.setVisible(true);

        totalTime = 0;
        resCoef = .02; //Drag factors are hardcoded
        grav = 9.8;
        mass = .145;
        radius = .0365;
        density = 1.225;

        pass = false;
        fall = false;
    }

    public void doStep() {
        Trail trail = new Trail();

        trail.color = new Color(44, 44, 44, 255);
        plotFrame.addDrawable(trail);

        if (circle.getX() >= 94.5 && !pass) { //If the ball passes the wall
            control.println("Ball cleared wall");
            pass = true;
        }

        if (circle.getY() <= 0 && circle.getX() <= 94.5 && falsey > 0 && !fall){
            control.println("Ball fell short of wall");
            control.println("Total distance traveled: " + circle.getX());
            fall = true;
        }
        if (circle.getY() <= 0 && circle.getX() >= 94.5 && falsex > 0 && !fall){
            control.println("Total distance traveled: " + circle.getX());
            fall = true;
        }

        if (circle.getX() <= 94.5 && circle.getX() + falsex*time >= 94.5 && (falsey/falsex)*(94.5-circle.getX())+circle.getY() <= 11.33){ //If the line between the two points crossses through the front edge of the wall
            circle.setY((falsey/falsex)*(94.5-circle.getX())+circle.getY());
            circle.setX(94.4);
            trail.addPoint(circle.getX(), circle.getY());
            yVelocity = .15 * yVelocity;
            xVelocity = -.15 * Math.abs(xVelocity);
            falsey=yVelocity;
            falsex=xVelocity;
            control.println("Ball bounced off wall");
            totalTime++;
        }

        if (circle.getY() > 0){ //stops when it hits the ground

            trail.addPoint(circle.getX(), circle.getY()); //draw dot

            double ydrag = (resCoef * density) * Math.pow(yVelocity, 2) * Math.PI * Math.pow(radius, 2) * time/(2 * mass);
            double xdrag = (resCoef * density) * Math.pow(xVelocity, 2) * Math.PI * Math.pow(radius, 2) * time/(2 * mass);

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
            if (falsex >= 0){
                double xchange = -xdrag;
                falsex = xVelocity + xchange/2;
                xVelocity = xVelocity + xchange; //true acceleration
            }
            else{
                double xchange = xdrag;
                falsex = xVelocity + xchange/2;
                xVelocity = xVelocity + xchange; //true acceleration
            }

            if (circle.getY() + falsey*time <=0){ //placing the ball directly on the x axis
                circle.setX(circle.getX() - (falsex/falsey)*(circle.getY()));
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
        SimulationControl.createApp(new FenwayParkApp());
    }
}
