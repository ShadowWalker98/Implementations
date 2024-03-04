import DynamicProgramming.ClassicalProblems;

public class Implementations {



    public static void main(String[] args) {
        ClassicalProblems cp = new ClassicalProblems();
        int[] nums = new int[] {0,0,0,0,0,0,0,0,1};

        System.out.println(cp.countNumSubsetsWithDiff(nums, 1));

    }

}
