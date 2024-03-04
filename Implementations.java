import DynamicProgramming.ClassicalProblems;

public class Implementations {



    public static void main(String[] args) {
        ClassicalProblems cp = new ClassicalProblems();
        int[] lengths = new int[] {1,2,3,4,5,6,7,8};
        int[] profit = new int[] {1,5,8,9,10,17,17,20};

        System.out.println(cp.rodCuttingDP(lengths, profit, 8));


    }

}
