package DynamicProgramming;

public class LongestCommonSubsequence {

    public LongestCommonSubsequence() {}

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
}
