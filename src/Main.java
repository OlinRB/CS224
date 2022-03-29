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
//        int A[] = {1,3};
//        int B[] = {2,4,5};
//        Object test[] = mergeAndCount(A,B);
//        int arr[] = (int[]) test[1];
//        for (int i = 0; i < arr.length; ++i) {
//            System.out.print(arr[i]);
//        }
    }

    public static Object[] sortAndCount(int values[]) {
        // fill this in
        int inversionCount = 0;
        int mergedList[] = new int[2];
        if (values.length > 2) {
            // Divide list in half
            int mid = values.length/2;
            int A[] = new int[mid];
            int B[] = new int[values.length-mid];
            for (int i = 0; i < mid; ++i)
                A[i] = values[i];
            for (int i = mid+1; i < values.length; ++i)
                B[i-mid] = values[i];

            // Sort and count on each half
            Object resultA[] = sortAndCount(A);
            inversionCount += (int) resultA[0];
            A = (int[]) resultA[1];
            Object resultB[] = sortAndCount(A);
            inversionCount += (int) resultB[0];
            B = (int[]) resultB[1];

            // Merge and count on product
            Object result[] = mergeAndCount(A,B);
            inversionCount += (int) result[0];
            mergedList = (int[]) result[1];
        }
        Object result[] = {inversionCount, values};
        return result;
    }

    public static Object[] mergeAndCount(int A[], int B[]) {
        // fill this in
        int numInversions = 0;
        int mergedArr[] = new int[A.length + B.length];
        int indexA = 0;
        int indexB = 0;
        int mergedIndex = 0;
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA] < B[indexB]) {
                mergedArr[mergedIndex] = A[indexA];
                ++indexA;
            } else {
                mergedArr[mergedIndex] = B[indexB];
                ++indexB;
                ++numInversions;
            }
            ++mergedIndex;
        }
        while (indexA < A.length) {
            mergedArr[mergedIndex] = A[indexA];
            ++indexA;
            ++mergedIndex;
        }
        while (indexB < B.length) {
            mergedArr[mergedIndex] = B[indexB];
            ++indexB;
            ++mergedIndex;
        }

        Object result[] = new Object[2];
        result[0] = numInversions;
        result[1] = mergedArr;
        return result;

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
