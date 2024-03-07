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


}
