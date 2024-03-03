import Strings.RabinKarp;


public class Implementations {



    public static void main(String[] args) {
        RabinKarp rp = new RabinKarp(101L, (long) (1e9 + 9));

        System.out.println(rp.rabinKarp("abc", "abcababc"));
    }

}
