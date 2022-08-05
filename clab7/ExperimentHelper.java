/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int height = (int) Math.log(N);
        int full_node = (int) Math.pow(2,height+1) - 1;
        int leaf = N - full_node;
        int path_length = 0;
        for (int i = 0; i <= height ; i++) {
            path_length = (int) Math.pow(2,i) * i + path_length;
        }
        path_length = (height + 1) * leaf + path_length;
        return path_length;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {

        return ((double)optimalIPL(N))/ (double)N;
    }
}
