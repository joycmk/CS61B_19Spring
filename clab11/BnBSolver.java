import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    List<Bear> bears;
    List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.bears = bears;
        this.beds = beds;

    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        Pair<ArrayList<Bear>,ArrayList<Bed>> sorted = quicksort(bears,beds);

        return sorted.first();
    }

    private Pair<ArrayList<Bear>,ArrayList<Bed>> quicksort(List<Bear> bear,List<Bed> bed) {
        if (bear.size()==1) {
            return new Pair(bear,bed);
        }
        ArrayList<Bear> less_bear = new ArrayList<>();
        ArrayList<Bear> equal_bear = new ArrayList<>();
        ArrayList<Bear> greater_bear = new ArrayList<>();
        ArrayList<Bed> less_bed = new ArrayList<>();
        ArrayList<Bed> equal_bed = new ArrayList<>();
        ArrayList<Bed> greater_bed = new ArrayList<>();

        Bed pivot_bed = bed.get(0);
        partition_bear(bear,pivot_bed,less_bear,equal_bear,greater_bear);

        Bear pivot_bear = equal_bear.get(0);
        partition_bed(bed,pivot_bear,less_bed,equal_bed,greater_bed);


        if (less_bear.size()!=0) {
            Pair<ArrayList<Bear>,ArrayList<Bed>> temp_less = quicksort(less_bear,less_bed);
            less_bear = temp_less.first();
            less_bed = temp_less.second();
        }
        if (greater_bear.size()!=0) {
            Pair<ArrayList<Bear>,ArrayList<Bed>> temp_greater = quicksort(greater_bear,greater_bed);
            greater_bear = temp_greater.first();
            greater_bed = temp_greater.second();
        }
        less_bear.addAll(equal_bear);
        less_bear.addAll(greater_bear);
        less_bed.addAll(equal_bed);
        less_bed.addAll(greater_bed);

        return new Pair(less_bear,less_bed);
    }

    private  void partition_bear(
            List<Bear> unsorted, Bed pivot,
            List<Bear> less, List<Bear> equal, List<Bear> greater) {
        // Your code here!
        for (int i=0; i<unsorted.size() ; i++) {
            Bear temp = unsorted.get(i);
            if (temp.compareTo(pivot) < 0) {
                less.add(temp);
            } else if (temp.compareTo(pivot)>0) {
                greater.add(temp);
            } else {
                equal.add(temp);
            }
        }
    }

    private  void partition_bed(
            List<Bed> unsorted, Bear pivot,
            List<Bed> less, List<Bed> equal, List<Bed> greater) {
        // Your code here!
        for (int i=0; i<unsorted.size() ; i++) {
            Bed temp = unsorted.get(i);
            if (temp.compareTo(pivot) < 0) {
                less.add(temp);
            } else if (temp.compareTo(pivot)>0) {
                greater.add(temp);
            } else {
                equal.add(temp);
            }
        }
    }
    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        Pair<ArrayList<Bear>,ArrayList<Bed>> sorted = quicksort(bears,beds);

        return sorted.second();
    }
}
