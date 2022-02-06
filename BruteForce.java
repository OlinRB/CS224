import java.util.Arrays;
import java.lang.Math;
import java.io.*;
public class BruteForce {
    public static void main(String[] args) {
        int size = 3;
        int limit = 15;
        int weights[] = new int[]{5,3,7,3,4,12,9,4,5,2,6,7,1};
        int value[] = new int[]{2,1,1,8,1,5,4,5,4,3,4,2,6};
//        int weights[] = new int[]{5,3,7};
//        int value[] = new int[]{2,1,1};

        optimize(weights, value, limit);
    }


    public static void optimize(int weights[], int values[], int limit) {
        int N, index, j, x, val;
        boolean debug = false;
        N = weights.length;
        int end = (int) Math.pow(2, N);
        boolean choose[];
        boolean bestSub[];
        choose = new boolean[N];
        bestSub = new boolean[N];
        int bestVal = 0;
        int tWeight = 0;
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
            if (debug) {
                System.out.print(Arrays.toString(choose));
                System.out.print(" Index: ");
                System.out.println(index);
            }
            for (j = 0; j < (N); ++j) {
                if (choose[j]) {
                    totalValue += values[j];
                    totalWeight += weights[j];
                }
            }
            if (totalWeight < (limit+1) && totalValue > bestVal) {
                bestVal = totalValue;
                tWeight = totalWeight;
                for (j = 0; j < choose.length; ++j) {
                    bestSub[j] = choose[j];
                }
            }
            totalValue = 0;
            totalWeight = 0;

            // now, build the ith subset of the items:
            // for j in 0 to N-1, include item j if choose[j] is true
            // if the total weight of the objects in the subset is not greater than the weight limit
            // then compute the total value of the objects in the subset
            // keep track of the best subset seen
        }
        for (j = 0; j < bestSub.length; ++j) {
            if (bestSub[j]) {
            	System.out.println("Item: " + (j+1) + " \tWeight: " + (weights[j]) + " \tValue: " + (values[j]));
            }
        }
        
        System.out.print("Total Weight: " + tWeight + " | Total Value: " + bestVal);
    }
}
