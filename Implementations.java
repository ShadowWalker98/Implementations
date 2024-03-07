import DynamicProgramming.ClassicalProblems;

public class Implementations {



    public static void main(String[] args) {
        ClassicalProblems cp = new ClassicalProblems();
        int[] lengths = new int[] {1,2,3,4,5,6,7,8};
        int[] profit = new int[] {1,5,8,9,10,17,17,20};

        int[] coins = new int[] {1, 2, 5};

        System.out.println(cp.minInsertionsToMakeItPalindrome("abc"));

//        System.out.println(cp.rodCuttingDP(lengths, profit, 8));
//        System.out.println(cp.coinChangeTwo(new int[] {1, 2, 5}, 5));

//        System.out.println(cp.minNumberCoins(coins, 11));

//        System.out.println(cp.longestCommonSubsequence("abc", "cbc", 3, 3));

//        System.out.println(cp.shortestCommonSuperSequence("abac", "cab"));

//        System.out.println(cp.sequencePatternMatching("abd", "azbcasc"));



    }

}
