package hw3.hash;

import java.util.HashSet;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucket = new int[M];
        int N = oomages.size();

        for (int i = 0; i < N ; i++ ) {
            Oomage o = oomages.get(i);
            if(o.getClass() == SimpleOomage.class) {
                ((SimpleOomage) o).PerfectHash();
            }
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum] = bucket[bucketNum] + 1;
        }

        for (int i = 0; i < M ; i++ ) {
            if (bucket[i] <= ((double)N)/50.0 || bucket[i] >= ((double)N)/2.5) {
                return false;
            }
        }
        return true;
    }
}
