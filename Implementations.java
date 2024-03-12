import DynamicProgramming.ClassicalProblems;
import Graphs.GraphDriver;
import Strings.SlidingWindow;

import java.util.Arrays;

public class Implementations {

    public static void main(String[] args) {
        ClassicalProblems cp = new ClassicalProblems();
        int[] lengths = new int[] {1,2,3,4,5,6,7,8};
        int[] profit = new int[] {1,5,8,9,10,17,17,20};

        int[] coins = new int[] {1, 2, 5};

//        System.out.println(cp.minInsertionsToMakeItPalindrome("abc"));

//        System.out.println(cp.rodCuttingDP(lengths, profit, 8));
//        System.out.println(cp.coinChangeTwo(new int[] {1, 2, 5}, 5));

//        System.out.println(cp.minNumberCoins(coins, 11));

//        System.out.println(cp.longestCommonSubsequence("abc", "cbc", 3, 3));

//        System.out.println(cp.shortestCommonSuperSequence("abac", "cab"));

//        System.out.println(cp.sequencePatternMatching("abd", "azbcasc"));

//        System.out.println(cp.palindromePartitioning("tiitk"));

        GraphDriver gd = new GraphDriver();

//        System.out.println(Arrays.toString(gd.courseSchedulingTwo(preqreq, 4)));

        int[][] graph = new int[4][];

        graph[0] = new int[]{1,2};
        graph[1] = new int[]{3};
        graph[2] = new int[]{3};
        graph[3] = new int[]{};

//        System.out.println(gd.allPathsSrcTarget(graph));

        SlidingWindow sw = new SlidingWindow();

//        System.out.println(sw.countAnagrams("abab", "ab"));

//        System.out.println(sw.maxOfAllSubarraysOfK(new int[]{-7,-8,7,5,7,1,6,0}, 4));

//        System.out.println(sw.maxDistinctSubarraySum(new int[]{1,5,4,2,9,9,9}, 3));

        int[][] grid = new int[6][6];

        for(int i = 0; i < grid.length; i++) {
            grid[i][i] = 0;
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[i][j] = Integer.MAX_VALUE;

                if(i == j) {
                    grid[i][j] = 0;
                }
            }
        }

        grid[0][1] = 2;
        grid[0][2] = 1;
        grid[0][3] = 3;

        grid[1][2] = 4;
        grid[1][5] = 3;

        grid[2][3] = 6;
        grid[2][4] = 1;
        grid[2][5] = 3;

        grid[3][4] = 2;

        grid[5][4] = 3;

//        gd.dijkstras(grid, 0);

        int[][] times = new  int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(gd.networkDelay(times, 4, 2));

        int[][] times2 = new  int[][]{{1,2,1}};
        System.out.println(gd.networkDelay(times2, 2, 1));

        int[][] times3 = new  int[][]{{1,2,1}};
        System.out.println(gd.networkDelay(times3, 2, 2));


    }


    public static String removeX(String N, char X)
    {
        // Stores the index of X
        // that has to be removed
        int index = -1;

        // Find leftmost occurrence of X
        // such that the digit just after X
        // is greater than X
        for (int i = 0; i < N.length() - 1; i++) {
            if (N.charAt(i) == X
                    && N.charAt(i) - '0'
                    < N.charAt(i + 1) - '0') {

                // Update index and break
                index = i;
                break;
            }
        }

        // If no occurrence of X such that
        // the digit just after X
        // is greater than X is found
        // then find last occurrence of X
        if (index == -1) {
            for (int i = N.length() - 1; i >= 0; i--) {
                if (N.charAt(i) == X) {
                    index = i;
                    break;
                }
            }
        }

        // Construct answer using all characters
        // in string N except index
        String ans = "";
        for (int i = 0; i < N.length(); i++) {
            if (i != index)
                ans = ans + N.charAt(i);
        }

        return ans;
    }

    public static String removeXNeg(String N, char X)
    {
        // Stores the index of X
        // that has to be removed
        int index = -1;

        // Find leftmost occurrence of X
        // such that the digit just after X
        // is greater than X
        for (int i = 0; i < N.length() - 1; i++) {
            if (N.charAt(i) == X
                    && N.charAt(i) - '0'
                    > N.charAt(i + 1) - '0') {

                // Update index and break
                index = i;
                break;
            }
        }

        // If no occurrence of X such that
        // the digit just after X
        // is greater than X is found
        // then find last occurrence of X
        if (index == -1) {
            for (int i = N.length() - 1; i >= 0; i--) {
                if (N.charAt(i) == X) {
                    index = i;
                    break;
                }
            }
        }

        // Construct answer using all characters
        // in string N except index
        String ans = "";
        for (int i = 0; i < N.length(); i++) {
            if (i != index)
                ans = ans + N.charAt(i);
        }

        return ans;
    }

}
