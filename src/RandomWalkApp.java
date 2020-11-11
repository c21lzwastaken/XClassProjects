import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.PlotFrame;
import org.opensourcephysics.controls.SimulationControl;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;


public class RandomWalkApp extends AbstractSimulation {
    PlotFrame plotFrame = new PlotFrame("x", "y", "Moving Ball");
    ArrayList circle = new ArrayList<Circle>();
    double totalTime;

    @Override
    public void reset() {
        /*
        control.setValue("Starting Y position", 100);
        control.setValue("Starting X position", 100);
        */
    }

    @Override
    public void initialize() {
        Random rand = new Random();
        // Get information from the control panel.
        /*
        double startingY = control.getDouble("Starting Y position");
        circle.setY(startingY);
        double startingX = control.getDouble("Starting X position");
        circle.setX(startingX);

         */

        for (int i = 0; i < 50; i++) {
            Circle circ = new Circle();
            circ.setX(rand.nextInt(100)-50);
            circ.setY(rand.nextInt(100)-50);
            circle.add(i, circ);
            plotFrame.addDrawable(circ);
        }

        // Instead of appending x, y coordinates to plot frame,
        //    add the Circle which maintains its own x, y.
        //plotFrame.addDrawable(circle);

        // Configure plot frame
        plotFrame.setPreferredMinMax(-100, 100, -100, 100); // Scale of graph.
        plotFrame.setDefaultCloseOperation(3); // Make it so x'ing out of the graph stops the program.
        plotFrame.setVisible(true); // Required to show plot frame.
    }

    public void doStep() {
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            ((Circle) circle.get(i)).setX(((Circle) circle.get(i)).getX() + (rand.nextInt(3)-1));
            System.out.println(rand.nextInt(3)-1);
            ((Circle) circle.get(i)).setY(((Circle) circle.get(i)).getY() + (rand.nextInt(3)-1));
        }

        // Change y. (It will re-draw itself.)

        //circle.setY(circle.getY() - (rand.nextInt(2)-1));
        //circle.setX(circle.getX() + (rand.nextInt(2)-1));

        totalTime++;
    }

    @Override
    public void stop(){
        //System.out.println(totalTime/10 + " secs to travel " + Math.abs(control.getDouble("Starting Y position") - circle.getY())+ " meters.");
    }

    public static void main(String[] args) {
        SimulationControl.createApp(new RandomWalkApp());
    }
}
