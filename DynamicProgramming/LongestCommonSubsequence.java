package DynamicProgramming;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public LongestCommonSubsequence() {}

    public int lcsMemo(String a, String b, int idx1, int idx2) {
        int[][] memo = new int[a.length() + 1][b.length() + 1];

        for(int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return lcsMemoHelper(a, b, idx1, idx2, memo);
    }

    public int lcsRec(String a, String b, int idx1, int idx2) {
        if(idx1 == 0 || idx2 == 0) {
            return 0;
        }

        if(a.charAt(idx1 - 1) == b.charAt(idx2 - 1)) {
            return 1 + lcsRec(a, b, idx1 - 1, idx2 - 1);
        } else {
            return Math.max(lcsRec(a, b, idx1 - 1, idx2), lcsRec(a, b, idx1, idx2 - 1));
        }
    }

    private int lcsMemoHelper(String a, String b, int idx1, int idx2, int[][] memo) {
        if(idx1 == 0 || idx2 == 0) {
            return 0;
        }

        if(memo[idx1][idx2] != -1) {
            return memo[idx1][idx2];
        }

        if(a.charAt(idx1 - 1) == b.charAt(idx2 - 1)) {
            memo[idx1][idx2] = 1 + lcsMemoHelper(a, b, idx1 - 1, idx2 - 1, memo);
        } else {
            memo[idx1][idx2] = Math.max(lcsMemoHelper(a, b, idx1, idx2 - 1, memo),
                    lcsMemoHelper(a, b, idx1 - 1, idx2, memo));
        }

        return memo[idx1][idx2];
    }


    public String shortestCommonSupersequence(String str1, String str2) {
        // first get the longest common substring
        // then add the other characters in order

        // first let's get the lcs of both the strings

        String lcstr = lcs(str1, str2);

        if(lcstr.length() < 1) {
            return str1 + str2;
        }

        return superSequenceBuilder(str1, str2, lcstr);
    }

    private String superSequenceBuilder(String a, String b, String lcs) {
        int t1 = 0;
        int t2 = 0;
        int tl = 0;

        StringBuilder sb = new StringBuilder();

        while(tl < lcs.length()) {
            if(a.charAt(t1) == b.charAt(t2) && a.charAt(t1) == lcs.charAt(tl)) {
                sb.append(String.valueOf(a.charAt(t1)));
                t1++;
                t2++;
                tl++;
            } else {
                while(a.charAt(t1) != lcs.charAt(tl)) {
                    sb.append(String.valueOf(a.charAt(t1)));
                    t1++;
                }

                while(b.charAt(t2) != lcs.charAt(tl)) {
                    sb.append(String.valueOf(b.charAt(t2)));
                    t2++;
                }
            }
        }

        while(t1 < a.length()) {
            sb.append(String.valueOf(a.charAt(t1)));
            t1++;
        }

        while(t2 < b.length()) {
            sb.append(String.valueOf(b.charAt(t2)));
            t2++;
        }

        return sb.toString();
    }

    private String lcs(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        Arrays.fill(dp[0], 0);
        for(int j = 0; j < dp.length; j++) {
            dp[j][0] = 0;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int m = a.length();
        int n = b.length();

        while(m > 0 && n > 0) {
            if(a.charAt(m - 1) == b.charAt(n - 1)) {
                sb.append(a.charAt(m - 1));
                m--;
                n--;
            } else {
                if(dp[m - 1][n]  > dp[m][n - 1]) {
                    m--;
                } else {
                    n--;
                }
            }
        }

        return sb.reverse().toString();
    }

    public int minInsertionsToMakePalindrome(String s) {
        String lcs = lcs(s, new StringBuilder(s).reverse().toString());
        return s.length() - lcs.length();
    }

    public int longestCommonSubstring(String a, String b) {
        int[][] dp = new int[a.length()][b.length()];

        Arrays.fill(dp[0], 0);

        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        int maxLength = Integer.MIN_VALUE;

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                }
            }
        }

        return maxLength;
    }

    // is String a contained as a subsequence in String b?
    public boolean sequencePatternMatching(String a, String b) {
        int al = a.length();
        int bl = b.length();
        int[][] dp = new int[al + 1][bl + 1];

        Arrays.fill(dp[0], 0);
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // we don't need to get the LCS and compare it specifically with a
        // we can just compare it and return based on that

        // String lcs = getLCS(dp, a, b);

        return a.length() == dp[al][bl];
    }


    private String getLCS(int[][] dp, String a, String b) {
        int i = a.length();
        int j = b.length();

        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0) {
            if(a.charAt(i - 1) == b.charAt(j - 1)) {
                sb.append(a.charAt(i -1));
                i--;
                j--;
            } else {
                if(dp[i][j] == dp[i - 1][j]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        String lcs = sb.reverse().toString();
        System.out.println(lcs);

        return lcs;
    }


}
