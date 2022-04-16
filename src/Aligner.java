import java.util.function.BiFunction;

public class Aligner {

	// 2D Array
	float[][] A;
	// BiFunction
	BiFunction<Character, Character, Float> biFunction;
	// Delta
	float delta;
	// Both Strings
	String stringA;
	String stringB;
	// Cost
	float cost;


	public Aligner(BiFunction<Character, Character, Float> biFunction, float delta) {
		this.biFunction = biFunction;
		this.delta = delta;
	}


	public float align(String stringA, String stringB) {
		this.stringA = stringA;
		this.stringB = stringB;
		this.A = new float[this.stringA.length()+1][this.stringB.length()+1];
		this.cost = 0.0f;


		int gapPenalty = 0;
		for (int i = 0; i <= this.stringA.length(); i++) {
			this.A[i][0] = gapPenalty;
			gapPenalty += this.delta;
		}
		gapPenalty = 0;
		for (int j = 0; j <= this.stringB.length(); j++) {
			this.A[0][j] = gapPenalty;
			gapPenalty += this.delta;
		}
		
		// Fill in memoization array calling opt function using for loop
		for (int i = 1; i <= this.stringA.length(); i++) {
			for (int j = 1; j <= this.stringB.length(); j++) {
				this.A[i][j] = this.opt(i,j);
			}
		}

		// Call traceback function to print alignments
		this.traceback(this.stringB.length(), this.stringB.length());
		// Calculate cost from A
		this.cost = this.A[this.stringA.length()][this.stringB.length()];
		// Return cost
		return this.cost;
	}

	public float smallestVal(int i, int j){
		float a, b, c, min;
		a = this.biFunction.apply(this.stringA.charAt(i-1),
				this.stringB.charAt(j-1)) + this.opt(i-1, j-1);
		b = this.delta + this.opt(i-1, j);
		c = this.delta + this.opt(i, j-1);
		// Determine smallest of a b c
		min = Math.min(Math.min(a, b), c);
		return min;
	}

	public float opt(int i, int j) {

		// Return 0 at start
		if ( i == 0 && j == 0 ) {
			return 0.0f;
		}
		// Check if val in A is zero else return
		if (this.A[i][j] != 0 ) {
			return this.A[i][j];
		}
		// Else find and return smallest value
		// Return smallest
		return smallestVal(i,j);
	}

	private String traceback(int i, int j) {
		// Clearly show alignment of each char in each string

		float a, b, c, min;

		// Return if you're at the starting node of the memoization array
		if ( i == 0 && j == 0 ) { 
			return "";
		}

		a = this.biFunction.apply(this.stringA.charAt(i-1), this.stringB.charAt(j-1)) + this.opt(i-1, j-1);
		b = this.delta + this.opt(i-1, j);
		c = this.delta + this.opt(i, j-1);
		min = Math.min(Math.min(a, b), c);

		if ( min == a ) {
			return this.traceback(i-1,j-1);
		}
		else if ( min == b ) {
			return this.traceback(i-1,j);
		}
		else if ( min == c ) {
			return this.traceback(i,j-1);
		}

		return ""; //
	}
}