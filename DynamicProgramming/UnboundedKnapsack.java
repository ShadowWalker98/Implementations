package DynamicProgramming;

import java.util.Arrays;

public class UnboundedKnapsack {

    public UnboundedKnapsack() {}

    public int unboundedKnapsack(int[] wt, int[] val, int capacity) {
        return unboundedKnapsackRec(wt, val, capacity, wt.length);
    }


    public int unboundedKnapsackRec(int[] wt, int[] val, int capacity, int idx) {
        if(capacity == 0) {
            return 0;
        }

        if(idx == 0) {
            return 0;
        }

        if(wt[idx - 1] > capacity) {
            return unboundedKnapsackRec(wt, val, capacity, idx - 1);
        } else {
            return Math.max(unboundedKnapsackRec(wt, val, capacity, idx - 1),
                    unboundedKnapsackRec(wt, val, capacity - wt[idx - 1], idx) + val[idx - 1]);

        }

    }


    public int unboundedKnapsackMemo(int[] wt, int[] val, int capacity, int idx, int[][] memo) {
        if(capacity == 0) {
            return 0;
        }

        if(idx == 0) {
            return 0;
        }

        if(memo[idx][capacity] != -1) {
            return memo[idx][capacity];
        }

        if(wt[idx - 1] > capacity) {
            memo[idx][capacity] = unboundedKnapsackMemo(wt, val, capacity, idx - 1, memo);
        } else {
            memo[idx][capacity] = Math.max(unboundedKnapsackMemo(wt, val, capacity, idx - 1, memo),
                    unboundedKnapsackMemo(wt, val, capacity - wt[idx - 1], idx, memo) + val[idx - 1]);
        }
        return memo[idx][capacity];
    }

    public int unboundedKnapsackDP(int[] wt, int[] val, int capacity) {
        int[][] dp = new int[wt.length + 1][capacity + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0) {
                    dp[i][j] = 0;
                }
                if(j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(wt[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[wt.length][capacity];
    }


    public int rodCuttingDP(int[] lengths, int[] profit, int L) {
        int size = lengths.length;

        int[][] dp = new int[size + 1][L + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0) {
                    dp[i][j] = 0;
                }
                if(j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(lengths[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - lengths[i - 1]] + profit[i - 1]);
                }
            }
        }
        return dp[size][L];
    }

    public int maxWaysCoins(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0) {
                    dp[i][j] = 0;
                }

                if(j == 0) {
                    dp[i][j] = 1;
                }
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }


    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        Arrays.fill(dp[0], Integer.MAX_VALUE - 1);

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }

        return dp[coins.length][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[coins.length][amount];
    }
}
