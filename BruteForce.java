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
        //System.out.println(isKthBitSet(1,0));
    }

    public static boolean isKthBitSet(int n,
                                   int k)
    {
        return (n & (1 << (k - 1))) > 0;
    }

    public static void optimize(int weights[], int values[], int limit) {
        int N, index, j, x, val;
        N = weights.length;
        int end = (int) Math.pow(2, N);
        boolean choose[];
        boolean bestSub[];
        choose = new boolean[N];
        bestSub = new boolean[N];
        int bestVal = 0;
        int totalValue = 0;
        int totalWeight = 0;
        for (index = 0; index < end; ++index) {
            for (j = 0; j < (N); ++j) {
                choose[j] = false;
            }
            for (j = 0; j < (N); ++j) {
                val = (int) Math.pow(2, j);
                if ((index & val) > 0) {
                    choose[j] = true;
                }
            }
            System.out.print(Arrays.toString(choose));
            System.out.print(" Index: ");
            System.out.println(index);
            for (j = 0; j < (N-1); ++j) {
                if (choose[j])
                    totalValue += values[j];
                    totalWeight += weights[j];
            }
            if (totalWeight < 16 && totalValue > bestVal) {
                bestVal = totalValue;
                bestSub = choose;
            }
            totalValue = 0;
            totalWeight = 0;

            // now, build the ith subset of the items:
            // for j in 0 to N-1, include item j if choose[j] is true
            // if the total weight of the objects in the subset is not greater than the weight limit
            // then compute the total value of the objects in the subset
            // keep track of the best subset seen
        }
        System.out.print("Items ");
        System.out.print("| ");
        for (j = 0; j < bestSub.length; ++j) {
            if (bestSub[j]) {
                System.out.print(j);
                System.out.print(", ");

            }
        }
        System.out.print(" |");
        System.out.print("= ");
        System.out.print(bestVal);

    }
        public static void test() {
            int N, index, j, x;
            N = 2;
            int end = (int) Math.pow(2, N);
            for (index = 0; index < end-1 ; ++index) {
                System.out.print("\n");
                x = 1;
                for (j = 0; j < (N-1); ++j) {
                    System.out.print(index & x);
                    System.out.print(" ");
                    if (x == 0)
                        x = 1;
                    else
                        x *= 2;
                }

            }
        }
}
