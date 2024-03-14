package Arrays.Strings;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {


    // p is the prime number used for computing the rolling hash
    long p;
    // m is the number for doing the module operation
    long m;

    public RabinKarp(long p, long m) {
        this.p = p;
        this.m = m;
    }

    public List<Integer> rabinKarp(String s, String t) {
        long S = s.length();
        long T = t.length();

        long[] pPow = new long[Math.max(s.length(), t.length())];
        pPow[0] = 1;
        for(int i = 1; i < pPow.length; i++) {
            pPow[i] = (pPow[i - 1] * p) % m;
        }

        long[] h = new long[(int)T + 1];

        for(int i = 0; i < T; i++) {
            h[i + 1] = (h[i] + (t.charAt(i) - 'a' + 1) * pPow[i]) % m;
        }

        long hashS = 0;

        for(int i = 0; i < S; i++) {
            hashS = (hashS + (s.charAt(i) - 'a' + 1) * pPow[i]) % m;
        }

        List<Integer> occurrences = new ArrayList<>();

        for(int i = 0; i + S - 1 < T; i++) {
            long currHash =  (h[(int) (i + S)] + m - h[i]) % m;
            if(currHash == hashS * pPow[i] % m) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }
}
