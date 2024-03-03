package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Backtracking {

    List<List<Integer>> combos;
    List<List<Integer>> permutations;

    List<List<Integer>> ans;
    public Backtracking() {
        combos = new ArrayList<>();
        permutations = new ArrayList<>();
        ans = new ArrayList<>();
    }

    public void nextPermutation(int[] nums) {
        if(nums.length == 1) {
            return;
        }
        int start = nums.length - 1;
        int searcher = nums.length - 2;
        boolean same = false;
        while(searcher > -1 && nums[searcher] >= nums[searcher + 1]) {
            if(nums[searcher] == nums[searcher + 1]) {
                same = true;
            }
            searcher--;
        }

        if(searcher == start - 1 || same) {
            swap(searcher, start, nums);

            return;
        }

        Integer nextNum = null;

        if(searcher != -1) {
            nextNum = searcher + 1;
            for(int i = searcher + 1; i < nums.length; i++) {
                if(nums[i] > nums[searcher] && nums[i] < nums[nextNum]) {
                    nextNum = i;
                }
            }
        }

        if(nextNum != null) {
            swap(searcher, nextNum, nums);
        }
        // we have to use two pointers to swap end and start of the array



        int swapper = searcher + 1;

        while(swapper <= start) {
            swap(swapper, start, nums);
            swapper++;
            start--;
        }
    }

    private void swap(int idx1, int idx2, int[] nums) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public void combine(int n, int k) {
        for(int i = 1; i <= n; i++) {
            createCombs(n, k, i, new ArrayList<>());
        }
        // we want to include the element and then not include it.
        // if the length of the list is k, then we don't need to add more elements.
        System.out.println(combos);
    }

    private void createCombs(int n, int k, int curr, List<Integer> list) {
        list.add(curr);

        if(list.size() == k) {
            combos.add(new ArrayList<>(list));
        }
        for(int i = curr + 1; i <= n; i++) {
            createCombs(n, k, i, list);
        }

        list.remove(list.size() - 1);
    }

    public void permute(int n, int k) {
        for(int i = 1; i <= n; i++) {
            createPermutations(n, k, i, new ArrayList<>(), new HashSet<>());
        }
        System.out.println(permutations);
    }

    public void permute(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            createPermutations(nums, i, new ArrayList<>(), new HashSet<>());
        }
        System.out.println(ans);
    }

    private void createPermutations(int n, int k, int curr, List<Integer> list, Set<Integer> set) {

        list.add(curr);
        set.add(curr);

        if(list.size() == k) {
            permutations.add(new ArrayList<>(list));
        }

        for(int i = 1; i <= n; i++) {
            if(!set.contains(i)) {
                createPermutations(n, k, i, list, set);
            }
        }

        set.remove(curr);
        list.remove(list.size() - 1);
    }

    private void createPermutations(int[] nums, int idx, List<Integer> list, Set<Integer> set) {
        list.add(nums[idx]);
        set.add(idx);

        if(list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
        }

        for(int i = 0; i < nums.length; i++) {
            if(!set.contains(i)) {
                createPermutations(nums, i, list, set);
            }
        }

        list.remove(list.size() - 1);
        set.remove(idx);

    }


}
