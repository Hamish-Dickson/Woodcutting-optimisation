import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static int woodUsed = 1;
    private static final int woodLengthInit = 3000;

    //collection of lengths to be cut (millimeters) range: 100-1500
    private static final int[] orders = {1102, 1102, 1081,
            1081, 1076, 790, 787, 786, 571, 569, 567, 564, 469, 468, 468, 467, 467, 467, 467, 467, 467, 467, 467, 467,
            467, 467, 467, 467, 467, 467, 467, 466, 466, 466, 466, 466, 466, 466, 466, 466, 466, 465, 465, 465, 465,
            465, 464, 464, 327, 325, 325, 324, 323, 323, 323, 323, 323, 323, 323, 323, 323, 323, 323, 323, 323, 322,
            322, 322, 322, 322, 322, 322, 322, 322, 321, 321, 321, 321, 321, 321, 321, 320, 320, 320, 320, 320, 320,
            320, 201, 200, 200, 200, 199};

    public static void main(String[] args) {
        findCuts();
    }

    private static void findCuts() {
        ArrayList<Integer> cuts = new ArrayList<>();
        ArrayList<Integer> listCutIndex = new ArrayList<>();
        ArrayList<Integer> residual = new ArrayList<>();
        int currentCutLength = woodLengthInit;
        int cutIndex = 0;

        for (int cut : orders) {
            /**System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("new job: cut " + cut + "mm");
            System.out.println("current length before check: " + currentCutLength);**/
            boolean cutFound = currentCutLength > cut;

            for (int i = 0; i < residual.size(); i++) {//let's check if we can cut using any of the wasted wood
                if (residual.get(i) > cut && !cutFound) {//if index i contains long enough wood
                    cutIndex = i;                       //set the current index of working wood
                    cutFound = true;                    //we found a cut
                    currentCutLength = residual.get(cutIndex); //set the current length of working wood
                    listCutIndex.add(cutIndex);         //add the index we will cut from to the list of indices
                }
            }
            if (!cutFound) {//if we didn't find a compatible piece
                woodUsed++; //increment the total panels used
                currentCutLength = woodLengthInit; //reset the cut length to a new panel
                listCutIndex.add(woodUsed); //add the index we will cut from to the list of indices
            }

            currentCutLength -= cut;
            cuts.add(cut);

            residual.add(cutIndex, currentCutLength);

            //System.out.println("current frames used: " + woodUsed);
        }
        System.out.println(listCutIndex);
        ArrayList<Frame> windows = collate(listCutIndex, cuts);
        for(Frame window: windows){
            window.print();
        }
    }

    private static ArrayList<Frame> collate(ArrayList<Integer> listCutIndex, ArrayList<Integer> cuts) {
        int numberOfFrames = Collections.max(listCutIndex);
        ArrayList<Frame> windows = new ArrayList<>();
        ArrayList<Integer> buffer = new ArrayList<>();

        for (int i = 0; i < numberOfFrames; i++) {
            for (int k = 0; k < listCutIndex.size(); k++) {
                if (listCutIndex.get(k) == i) {
                    buffer.add(cuts.get(k));
                }
            }
            windows.add(new Frame(buffer));
            buffer = new ArrayList<>();
        }
        return windows;
    }

}
