import java.util.ArrayList;
import java.util.function.BiFunction;

public class Aligner {

	// 2D Array
	float[][] A;
	// BiFunction
	BiFunction<Character, Character, Float> biFunction;
	// Delta
	float delta;
	// Start with gap penalty at 0
	int gapPenalty = 0;
	// Both Strings
	String stringA;
	String stringB;
	// Cost
	float cost = 0.0f;


	public Aligner(BiFunction<Character, Character, Float> biFunction, float delta) {
		this.biFunction = biFunction;
		this.delta = delta;
	}

	public float align(String string1, String string2) {
		this.stringA = string1;
		this.stringB = string2;
		A = new float[stringA.length()+1][stringB.length()+1];

		for (int i = 0; i <= stringA.length(); i++) {
			A[i][0] = gapPenalty;
			gapPenalty += delta;
		}
		// Reset gap for string B
		gapPenalty = 0;
		for (int j = 0; j <= stringB.length(); j++) {
			A[0][j] = gapPenalty;
			gapPenalty += delta;
		}
		
		// Fill in memoization array calling opt function using for loop
		for (int i = 1; i <= stringA.length(); i++) {
			for (int j = 1; j <= stringB.length(); j++) {
				A[i][j] = opt(i,j);
			}
		}
		// Call traceback function to print alignments
		traceback(stringA.length(), stringB.length());
		// Calculate cost from A
		cost = A[stringA.length()][stringB.length()];
		// Return cost
		return cost;
	}

	public float[] smallestVal(int i, int j){
		// Return min and case for traceback
		float[] retVals;
		retVals = new float[2];
		float a, b, c, min;
		int case_switch;
		// Bifunction + Opt i/j -1
		a = biFunction.apply(stringA.charAt(i-1),
				stringB.charAt(j-1)) + opt(i-1, j-1);
		// Delta + Opt i - 1
		b = delta + opt(i-1, j);
		// Delta + Opt j - 1
		c = delta + opt(i, j-1);
		// Determine smallest of a b c
		min = Math.min(Math.min(a, b), c);
		retVals[0] = min;
		if (min == a)
			case_switch = 1;
		else if (min == b)
			case_switch = 2;
		else
			case_switch = 3;
		retVals[1] = case_switch;
		return retVals;
	}

	public float opt(int i, int j) {

		// Return 0 at start
		if ( i == 0 && j == 0 )
			return 0.0f;
		// Check if val in A is zero else return
		if (A[i][j] != 0 )
			return A[i][j];
		// Else find and return smallest value
		// Return smallest
		return smallestVal(i,j)[0];
	}

	private String traceback(int i, int j) {
		// Clearly show alignment of each char in each string
		// Return if you're at the starting node of the memoization array
		if ( i == 0 && j == 0 ) { 
			return "";
		}

		int case_switch = (int)smallestVal(i,j)[1];

		switch (case_switch) {
			// Case for aligned chars
			// Print corresponding chars aligned
			case 1 -> {
				System.out.println(stringA.charAt(i - 1) + " aligns with " + stringB.charAt(j - 1));
				return traceback(i - 1, j - 1);
			}
			// Case for right gap
			case 2 -> {
				System.out.println(stringA.charAt(i - 1) + " aligns with -");
				return traceback(i - 1, j);
			}
			// Case for left gap
			case 3 -> {
				System.out.println("- aligns with " + stringB.charAt(j - 1));
				return traceback(i, j - 1);
			}
		}
		return "\n";
	}
}