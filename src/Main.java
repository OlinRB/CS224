// jdh CS224 Spring 2022

import java.util.function.BiFunction;

public class Main {
    public static void main(String[] argv) {
        testOne();
    }

    public static float getVal(char c1, char c2) {
//        αxy = 0 when x = y;
//        αxy = 1.0 when x and y are either both vowels or are both consonants; and αxy = 3 when x is a consonant
//        and y is a vowel, or vice versa
        String vowels = "aeiou";
        if (c1 == c2)
            return 0.0f;
        if ((vowels.indexOf(c1) == -1 && vowels.indexOf(c2) != -1) ||
                (vowels.indexOf(c1) != -1 && vowels.indexOf(c2) == -1))
            return 3.0f;
        else
            return 1.0f;
    }

  public static void testOne() {
    // this should produce the following alignment:
    // n-ame
    // mean-
    // with cost = 6.0
    float delta = 2.0f;
    String stringA = "mean";
    String stringB = "name";
    BiFunction <Character, Character, Float> alpha = Main::getVal;
    Aligner aligner = new Aligner(alpha, delta);
    float cost = aligner.align(stringA, stringB);
    // Print out cost
    System.out.print("cost = ");
    System.out.println(cost);
    System.out.println("\nArray:");
    // Print out mem array
    for (int i = 0; i < aligner.A.length; ++i) {
        for (int j = 0; j < aligner.A[i].length; ++j) {
            System.out.print(aligner.A[i][j]);
            System.out.print("|");
        }
        System.out.println("");
    }
  }

    public static void testTwo() {
        // this should give you the following alignment:
        // mowisthetime-al-lforgoodmen
        // nowisthedimeforall--goodmen
        // with cost = 13.0
        String s1 = "nowisthedimeforallgoodmen";
        String s2 = "mowisthetimeallforgoodmen";
        float delta = 2.0f;

        BiFunction <Character, Character, Float> alpha = Main::getVal;
        Aligner aligner = new Aligner(alpha, delta);
        float cost = aligner.align(s1, s2);
        System.out.print("cost = ");
        System.out.println(cost);
    }

}
