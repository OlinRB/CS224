// CS224 Spring 2022
// starter code for Assignment #4

public class Main {
    public static void main(String argv[]) {
        Object rtnval[] = testOne();
//  Object rtnval[] = testTwo();
//  Object rtnval[] = testThree();

        int arr[] = (int[]) rtnval[1];
        int numInversions = (int) rtnval[0];
        System.out.println("# inversions = " + numInversions);
        System.out.print("[");
        for (int i=0; i<arr.length; ++i)
            System.out.print(" " + arr[i]);
        System.out.println("]");
    }

    public static Object[] sortAndCount(int values[]) {
        // fill this in
    }

    public static Object[] mergeAndCount(int A[], int B[]) {
        // fill this in
    }

    public static Object[] testOne() {
        // 8 * 7 / 2 = 28 inversions
        int A[] = {8, 7, 6, 5, 4, 3, 2, 1};
        Object[] rtnval = sortAndCount(A);
        int count = (int) rtnval[0];
        if (count != 28) {
            System.out.println("Test One error: expect count = 28; got " + count);
        }
        return rtnval;
    }

    public static Object[] testTwo() {
        // 42 inversions
        int A[] = {2, 1, 16, 4, 12, 8, 6, 9, 11, 3, 13, 7, 14, 5, 10, 15};
        Object[] rtnval = sortAndCount(A);
        int count = (int) rtnval[0];
        if (count != 42) {
            System.out.println("Test Two error: expect count = 42; got " + count);
        }
        return rtnval;
    }

    public static Object[] testThree() {
        // five inversions
        int A[] = {3, 1, 5, 4, 2};
        Object[] rtnval = sortAndCount(A);
        int count = (int) rtnval[0];
        if (count != 5) {
            System.out.println("Test Three error: expect count = 5; got " + count);
        }
        return rtnval;
    }
}
