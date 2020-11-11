public class Scissors {
    int opennessDegree;
    double sharpnessPct;
    boolean isLefty;
    int handleSize;

    public Scissors (int opennessDegree, double sharpnessPct, boolean isLefty, int handleSize){
        this.opennessDegree = opennessDegree;
        this.sharpnessPct = sharpnessPct;
        this.isLefty = isLefty;
        this.handleSize = handleSize;
    }

    public void cut () {
        if (opennessDegree != 0){
            opennessDegree = 0;
            sharpnessPct = sharpnessPct - .01;
        }
        else {
            System.out.println("Scissors are already closed");
        }
    }
    public void sharpen () {
        sharpnessPct = 100;
    }

    }
