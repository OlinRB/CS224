import java.util.Arrays;
import java.lang.Math;
import java.io.*;
public class BruteForce {
    public static void main(String[] args) {
        int size = 3;
        int limit = 15;
        int weights[];
        int value[];
        weights = new int[size];
        value = new int[size];
        optimize(weights, value, limit);
        //test();
    }

    public static void optimize(int weights[], int values[], int limit) {
        int N, index, j;
        N = weights.length;
        int end = (int) Math.pow(2, N);
        boolean choose[];
        boolean bestSub[];
        choose = new boolean[N];
        bestSub = choose;
        for (index = 0; index < end ; ++index) {
            System.out.println(Arrays.toString(choose));
            for (j = 0; j < (N-1); ++j) {
                choose[j] = false;
            }
            for (j = 0; j < (N-1); ++j) {
                if ((index & (j+1)) > 0) {
                    choose[j] = true;
                    }
                }
            }


            // now, build the ith subset of the items:
            // for j in 0 to N-1, include item j if choose[j] is true
            // if the total weight of the objects in the subset is not greater than the weight limit
            // then compute the total value of the objects in the subset
            // keep track of the best subset seen
        }
        public static void test() {
            int i = 5;
            System.out.println(i & (i+1));

            int val = 1;
            for (int j = 0; j <4; ++j){
                System.out.println(j & val);
            }
        }
}
