package DynamicProgramming;

import java.util.Arrays;

public class ClassicalProblems {

    public ClassicalProblems() {}

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

    // refer to problem 494. Target Sum on leetcode
    // based off of subset with diff
    // didn't work, so I put the editorial solution
    public int targetSum(int[] nums, int target) {
        int range = 0;

        for(int num: nums) {
            range += num;
        }

        int[][] dp = targetSumHelper(nums, range);

        // target is basically diff
        int s1 = (range + target) / 2;

        return dp[nums.length][s1];
    }

    public int findTargetSumWays(int[] nums, int S) {
        int total = Arrays.stream(nums).sum();
        int[][] dp = new int[nums.length][2 * total + 1];
        dp[0][nums[0] + total] = 1;
        dp[0][-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            for (int sum = -total; sum <= total; sum++) {
                if (dp[i - 1][sum + total] > 0) {
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
                }
            }
        }

        for(int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return Math.abs(S) > total ? 0 : dp[nums.length - 1][S + total];
    }

    private int[][] targetSumHelper(int[] nums, int range) {

        int[][] dp = new int[nums.length + 1][range + 1];

        Arrays.fill(dp[0], 0);

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp;
    }

    public int countNumSubsetsWithDiff(int[] nums, int diff) {
        int total = 0;
        for(int num : nums) {
            total += num;
        }
        int[][] dp = subsetSumCountWithDiffHelper(nums, total);

        int numSubsets = 0;

        for(int j = 0; j <= total / 2; j++) {
            int currDiff = total - (2 * j);
            if(currDiff == diff) {
                numSubsets += dp[nums.length][j];
            }
        }
        return numSubsets;
    }

    public int[][] subsetSumCountWithDiffHelper(int[] nums, int range) {
        int[][] dp = new int[nums.length + 1][range + 1];

        Arrays.fill(dp[0], 0);

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {

                if(nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp;
    }
    public int minimumSubsetDiff(int[] nums) {
        int range = 0;
        for(int num : nums) {
            range += num;
        }

        boolean[][] dp = subsetDiffHelper(nums, range);

        int maxDiff = range;
        for(int j = 0; j <= range / 2; j++) {
            if(dp[nums.length][j]) {
                int diff = Math.abs(range - (2 * j));
                if(diff < maxDiff) {
                    maxDiff = diff;
                }
            }
        }

        return maxDiff;

    }

    public boolean[][] subsetDiffHelper(int[] nums, int range) {
        boolean[][] dp = new boolean[nums.length + 1][range + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0) {
                    dp[i][j] = false;
                }
                if(j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp;
    }

    public int zeroOneKnapsack(int[] weights, int[] values, int capacity) {
        return profitBottomUp(weights, values, capacity);
    }



    private int profit(int[] wt, int[] v, int cap, int idx) {
        if(idx == -1 || cap == 0) {
            return 0;
        }

        if(wt[idx] > cap) {
            return profit(wt, v, cap, idx - 1);
        } else {
            return Math.max(profit(wt, v, cap - wt[idx], idx - 1) + v[idx],
                    profit(wt, v, cap, idx - 1));
        }
    }

    private int profitDP(int[] wt, int[] v, int cap, int idx, int[][] memo) {
        if(idx == -1 || cap == 0) {
            return 0;
        }

        if(memo[idx][cap] != -1) {
            return memo[idx][cap];
        }

        if(wt[idx] > cap) {
            memo[idx][cap] = profit(wt, v, cap, idx - 1);
        } else {
            memo[idx][cap] = Math.max(profit(wt, v, cap - wt[idx], idx - 1) + v[idx],
                    profit(wt, v, cap, idx - 1));
        }

        return memo[idx][cap];
    }

    private int profitBottomUp(int[] wt, int[] values, int capacity) {
        int[][] dp = new int[wt.length + 1][capacity + 1];

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[i].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], j - wt[i - 1] >= 0 ? dp[i - 1][j - wt[i - 1]] + values[i - 1] : 0);
            }
        }

        return dp[wt.length][capacity];
    }


    public boolean subsetSum(int[] nums, int target) {
//        return subsetSumHelper(nums, target, nums.length);
//        int[][] memo = new int[target + 1][nums.length + 1];
//        for(int i = 0; i < memo.length; i++) {
//            for(int j = 0; j < memo[0].length; j++) {
//                memo[i][j] = -1;
//            }
//        }
//        return subsetSumMemo(nums, target, nums.length, memo);
        return subsetSumBottomUp(nums, target);
    }


    private boolean subsetSumHelper(int[] nums, int target, int idx) {
        if(target == 0) {
            return true;
        }

        if(idx == 0) {
            return false;
        }

        if(nums[idx - 1] > target) {
            return subsetSumHelper(nums, target, idx - 1);
        } else {
            return subsetSumHelper(nums, target, idx - 1) || subsetSumHelper(nums, target - nums[idx - 1], idx - 1);
        }
    }

    private boolean subsetSumMemo(int[] nums, int target, int idx, int[][] memo) {
        if(target == 0) {
            return true;
        }

        if(idx == 0) {
            return false;
        }

        if(memo[target][idx] != -1) {
            return memo[target][idx] == 1;
        }

        if(nums[idx - 1] > target) {
            memo[target][idx] = subsetSumMemo(nums, target, idx - 1, memo) ? 1 : 0;
        } else {
            memo[target][idx] = (subsetSumMemo(nums, target, idx - 1, memo) || subsetSumMemo(nums, target - nums[idx - 1], idx - 1, memo)) ? 1 : 0;
        }

        return memo[target][idx] == 1;
    }

    private boolean subsetSumBottomUp(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0) {
                    dp[i][j] = false;
                }
                if(j == 0) {
                    dp[i][j] = true;
                }
            }
        }


        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];
    }


    public boolean equalSumPartition(int[] nums) {
        int totalSum = 0;

        for(int num : nums) {
            totalSum += num;
        }

        if(totalSum % 2 == 1) {
            return false;
        } else {
            return equalSumPartitionBottomUp(nums, totalSum / 2);
        }
    }

    private boolean equalSumPartitionRec(int[] nums, int target, int idx) {
        if(target == 0) {
            return true;
        }

        if(idx == 0) {
            return false;
        }

        if(nums[idx - 1] > target) {
            return equalSumPartitionRec(nums, target, idx - 1);
        } else {
            return equalSumPartitionRec(nums, target, idx - 1) || equalSumPartitionRec(nums, target - nums[idx - 1], idx - 1);
        }
    }

    private boolean equalSumPartitionBottomUp(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0) {
                    dp[i][j] = false;
                }

                if(j == 0) {
                    dp[i][j] = true;
                }
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];
    }

    public int subsetSumCount(int[] nums, int target) {
        return subsetSumCountDP(nums, target);
    }

    private int subsetSumCountRec(int[] nums, int target, int idx) {
        if(target == 0) {
            return 1;
        }

        if(idx == 0) {
            return 0;
        }

        if(nums[idx - 1] > target) {
            return subsetSumCountRec(nums, target, idx - 1);
        } else {
            return subsetSumCountRec(nums, target, idx - 1) + subsetSumCountRec(nums, target - nums[idx - 1], idx - 1);
        }
    }

    private int subsetSumCount(int[] nums, int target, int idx, int[][] memo) {
        if(target == 0) {
            return 1;
        }

        if(idx == 0) {
            return 0;
        }

        if(nums[idx - 1] > target) {
            memo[idx][target] = subsetSumCount(nums, target, idx - 1, memo);
        } else {
            memo[idx][target] = subsetSumCount(nums, target, idx - 1, memo)
                    + subsetSumCount(nums, target - nums[idx -1], idx - 1, memo);
        }

        return memo[idx][target];
    }

    private int subsetSumCountDP(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];

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
                if(nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];
    }
}
