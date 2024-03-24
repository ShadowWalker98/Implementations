package DynamicProgramming;

import java.util.*;

public class ClassicalProblems {

    ZeroOneKnapsack zeroOneKnapsack;
    UnboundedKnapsack unboundedKnapsack;
    LongestCommonSubsequence lcs;

    MatrixChainMultiplication mcm;

    public ClassicalProblems() {
        zeroOneKnapsack = new ZeroOneKnapsack();
        unboundedKnapsack = new UnboundedKnapsack();
        lcs = new LongestCommonSubsequence();
        mcm = new MatrixChainMultiplication();
    }

    public int rodCuttingDP(int[] nums, int[] profit, int length) {
        return unboundedKnapsack.rodCuttingDP(nums, profit, length);
    }

    public int coinChangeTwo(int[] coins, int amount) {
        return unboundedKnapsack.maxWaysCoins(coins, amount);
    }

    public int minNumberCoins(int[] coins, int amount) {
        return unboundedKnapsack.coinChange(coins, amount);
    }

    public int longestCommonSubsequence(String a, String b, int idx1, int idx2) {
        return lcs.lcsMemo(a, b, idx1, idx2);
    }

    public String shortestCommonSuperSequence(String a, String b) {
        return lcs.shortestCommonSupersequence(a, b);
    }

    public boolean sequencePatternMatching(String a, String b) {
        return lcs.sequencePatternMatching(a, b);
    }


    /* Leetcode #64: Find the minimum path sum to exit out of the grid at (m, n)
    @param: m x n int[][] grid
    int[] weights = new int[] {2, 1, 3, 4};
    int[] values = new int[] {7, 4, 5, 6};
     */
    public int minimumPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                dp[i][j] =
                        Math.min(
                                isValid(i - 1, j, m, n) ? dp[i - 1][j] : Integer.MAX_VALUE,
                                isValid(i, j - 1, m, n) ? dp[i][j - 1] : Integer.MAX_VALUE
                        );
            }
        }

        return dp[m - 1][n - 1];
    }

    // helper method for minimum path sum (#64)
    private boolean isValid(int i, int j, int r, int c) {
        return ((i >= 0 && i < r) && (j >= 0 && j < c));
    }

    public int minInsertionsToMakeItPalindrome(String s) {
        return lcs.minInsertionsToMakePalindrome(s);
    }

    public int palindromePartitioning(String s) {
        return mcm.palindromePartitioning(s);
    }

    public int knightDialer(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < 10; i++) {
            map.put(i, new ArrayList<>());
        }

        map.get(1).add(6);
        map.get(1).add(8);

        map.get(2).add(9);
        map.get(2).add(7);

        map.get(3).add(4);
        map.get(3).add(8);

        map.get(4).add(3);
        map.get(4).add(9);
        map.get(4).add(0);

        map.get(6).add(1);
        map.get(6).add(7);
        map.get(6).add(0);

        map.get(7).add(2);
        map.get(7).add(6);

        map.get(8).add(1);
        map.get(8).add(3);

        map.get(9).add(2);
        map.get(9).add(4);

        map.get(0).add(4);
        map.get(0).add(6);

        int ans = 0;
        int mod = 1000000007;
        int[][] dp = new int[10][n + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        for(int i = 0; i < 10; i++) {
            dp[i][0] = 1;
        }

        for(int i = 0; i < 10; i++) {
            ans = (ans % mod +  knightHelper(i, n - 1, map, mod, dp) % mod) % mod;
        }

        return ans;
    }

    public int knightHelper(int pos, int movesLeft, Map<Integer, List<Integer>> map, int mod, int[][] dp) {

        if(dp[pos][movesLeft] != -1) {
            return dp[pos][movesLeft];
        }

        if(movesLeft == 1) {
            dp[pos][movesLeft] = map.get(pos).size();
            return dp[pos][movesLeft];
        }

        int total = 0;
        for(int jumpNode : map.get(pos)) {
            total = (total % mod + knightHelper(jumpNode, movesLeft - 1, map, mod, dp) % mod) % mod;
        }

        dp[pos][movesLeft] = total;

        return total;
    }

}
