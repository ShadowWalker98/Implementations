package DynamicProgramming;

public class UnboundedKnapsack {

    public UnboundedKnapsack() {}

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
}
