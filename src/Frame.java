import java.util.ArrayList;

public class Frame {
    static int id = 1;
    private int thisId;
    private int initSize = 3000;
    private ArrayList<Integer> cutsToMake;
    private int remainingSize = initSize;

    public Frame(ArrayList<Integer> cutsToMake) {
        this.thisId = id++;
        this.cutsToMake = cutsToMake;
        calcRemaining();
    }

    private void calcRemaining() {
        for (int cut : cutsToMake) {
            remainingSize -= cut;
        }
    }

    public int getThisId() {
        return thisId;
    }

    public ArrayList<Integer> getCutsToMake(){
        return cutsToMake;
    }

    public void print() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Frame number: " + thisId);
        System.out.println("Cuts to make: " + cutsToMake);
        System.out.println("Residual waste: " + remainingSize);
    }
}
