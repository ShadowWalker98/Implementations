package DynamicProgramming;

public class MatrixChainMultiplication {

    public MatrixChainMultiplication() {}


    public int mcmRec(int[] arr) {
        int i = 1;
        int j = arr.length - 1;
        return mcmRecHelper(arr, i, j);
    }

    private int mcmRecHelper(int[] arr, int i, int j) {

        if(i >= j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for(int k = i; k <= j - 1; k++) {
            int tempCost = mcmRecHelper(arr, i, k) + mcmRecHelper(arr, k + 1, j) + arr[i - 1] * arr[k] * arr[j];
            if(minCost > tempCost) {
                minCost = tempCost;
            }
        }

        return minCost;
    }

    public int mcmMemo(int[] arr) {
        int[][] memo = new int[1001][1001];

        for(int i = 0; i < memo.length; i++) {
            for(int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }

        return mcmHelperMemo(arr, 1, arr.length - 1, memo);
    }

    private int mcmHelperMemo(int[] arr, int i, int j, int[][] memo) {
        if(i >= j) {
            return 0;
        }

        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for(int k = i; k < j; k++) {
            int tempCost = mcmHelperMemo(arr, i, k, memo) + mcmHelperMemo(arr, k + 1, j, memo)
                    + arr[i - 1] * arr[k] * arr[j];
            if(minCost > tempCost) minCost = tempCost;
        }

        memo[i][j] = minCost;
        return minCost;
    }

    public int palindromePartitioning(String s) {
        char[] chars = s.toCharArray();
        return partitionHelper(chars, 0, s.length() - 1);
    }

    private int partitionHelper(char[] s, int i, int j) {
        if(i >= j) {
            return 0;
        }

        if(isPalindrome(s, i, j)) {
            return 0;
        }

        int minPartitions = Integer.MAX_VALUE;

        for(int k = i; k < j; k++) {
            int tempPartitions = 1 + partitionHelper(s, i, k) + partitionHelper(s, k + 1, j);
            if(tempPartitions < minPartitions) {
                minPartitions = tempPartitions;
            }
        }

        return minPartitions;
    }

    public int partitionHelperMemo(char[] s, int i, int j, int[][] memo) {
        if(i >= j) return 0;

        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        if(isPalindrome(s, i, j)) {
            memo[i][j] = 0;
            return 0;
        }

        int cost = Integer.MAX_VALUE;

        for(int k = i; k < j; k++) {
            int tempCost = 1 + partitionHelperMemo(s, i, k, memo) + partitionHelperMemo(s, k + 1, j, memo);

            if(tempCost < cost) {
                cost = tempCost;
            }
        }

        memo[i][j] = cost;

        return cost;
    }

    public int partitionHelperOptimised(char[] s, int i, int j, int[][] memo) {
        if (i >= j)
            return 0;

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (isPalindrome(s, i, j)) {
            memo[i][j] = 0;
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {

            if (isPalindrome(s, i, k)) {
                int partitions = 1 + partitionHelperOptimised(s, k + 1, j, memo);
                min = Math.min(min, partitions);
            }
        }

        memo[i][j] = min;

        return min;
    }


    private boolean isPalindrome(char[] s, int i, int j) {

        while(i <= j) {
            if(s[i] == s[j]) {
                i++;
                j--;
            } else {
                return false;
            }
        }

        return true;
    }

    // do ths later!!

//    public int countWaysToEvaluateTrue(char[] expr, int i, int j) {
//        return helperRec(expr, i, j, true);
//    }


    // Egg dropping!! I've been looking forward to learning this :P
    // I can't believe this is an MCM pattern problem thoo, I never thought it would be MCM
    // We have to find the critical floor by using the least number of attempts.
    // Threshold/critical floor is the lowest floor at which the egg breaks

    public int eggDropperRec(int numEggs, int numFloors) {
        return 0;
    }




}
