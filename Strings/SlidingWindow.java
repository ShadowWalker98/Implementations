package Strings;

import java.util.*;

public class SlidingWindow {

    public SlidingWindow() {}

    public List<Integer> countAnagrams(String s, String p) {

        List<Integer> indices = new ArrayList<>();

        if(p.length() > s.length()) {
            new ArrayList<Integer>();
        }

        Map<Character, Integer> pmap = new HashMap<>();

        for(char c : p.toCharArray()) {
            int count = pmap.getOrDefault(c, 0);
            pmap.put(c, count + 1);
        }

        Map<Character, Integer> smap = new HashMap<>();

        for(int i = 0; i < s.length() - p.length() + 1; i++) {
            if(i == 0) {
                // We build the map
                int track = i;
                while(track < p.length()) {
                    Character c = s.charAt(track++);
                    int count = smap.getOrDefault(c, 0);
                    smap.put(c, count + 1);
                }

                if(pmap.equals(smap)) {
                    indices.add(i);
                }
                continue;
            }

            Character added = s.charAt(i + p.length() - 1);
            Character deleted = s.charAt(i - 1);

            int countAdded = smap.getOrDefault(added, 0);
            smap.put(added, countAdded + 1);

            int countDeleted = smap.get(deleted);
            if(countDeleted - 1 == 0) {
                smap.remove(deleted);
            } else {
                smap.put(deleted, countDeleted - 1);
            }

            if(pmap.equals(smap)) {
                indices.add(i);
            }
        }
        return indices;
    }

    public List<Integer> maxOfAllSubarraysOfK(int[] arr, int k) {
        List<Integer> ans = new ArrayList<>();

        LinkedList<Integer> queue = new LinkedList<>();

        int n = arr.length;

        for(int i = 0; i < n - k + 1; i++) {
            if(i == 0) {
                for(int j = i; j < i + k; j++) {
                    int ele = arr[j];
                    if(!queue.isEmpty()) {
                        while(!queue.isEmpty() && queue.peekLast() < ele) {
                            queue.removeLast();
                        }
                    }
                    queue.addLast(ele);
                }
                ans.add(queue.peekFirst());
                continue;
            }

            int popEle = arr[i - 1];
            int addEle = arr[i + k - 1];

            if(!queue.isEmpty() && queue.peekFirst() == popEle) {
                queue.removeFirst();
            }
            while(!queue.isEmpty() && queue.peekLast() < addEle) {
                queue.removeLast();
            }
            queue.addLast(addEle);
            ans.add(queue.peekFirst());
        }

        return ans;
    }

    public long maxDistinctSubarraySum(int[] arr, int k) {

        long maxSum = 0;


        long currSum = 0;

        int n = arr.length;

        Map<Integer, Integer> counts = new HashMap<>();

        Set<Integer> dups = new HashSet<>();



        for(int i = 0; i < n - k + 1; i++) {

            if(i == 0) {
                for(int j = i; j < i + k; j++) {

                    int ele = arr[j];
                    currSum += ele;
                    int count = counts.getOrDefault(ele, 0);

                    if(count > 0) {
                        dups.add(ele);
                    }
                    counts.put(ele, count +  1);
                }

                if(dups.isEmpty()) {
                    maxSum = currSum;
                }

                continue;
            }

            int deleted = arr[i - 1];
            int added = arr[i + k - 1];

            currSum -= deleted;
            currSum += added;

            int delCount = counts.get(deleted);

            if(delCount - 1 > 0) {
                counts.put(deleted, delCount - 1);
                if(delCount - 1 == 1) {
                    dups.remove(deleted);
                }
            } else {
                counts.remove(deleted);
            }

            int addedCount = counts.getOrDefault(added, 0);

            if(addedCount > 0) {
                dups.add(added);
            }

            counts.put(added, addedCount + 1);

            if(dups.isEmpty()) {
                maxSum = Math.max(maxSum, currSum);
            }
        }
        return maxSum;
    }


}
