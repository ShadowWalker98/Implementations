package Graphs;

import java.util.*;

public class TopologicalSort {

    public TopologicalSort() {}

    public int[] courseScheduleTwo(int[][] prerequisites, int numCourses) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        int[] inDegrees = new int[numCourses];

        for(int[] prereq : prerequisites) {
            int to = prereq[0];
            int from = prereq[1];

            List<Integer> edgeList = adjList.getOrDefault(from, new ArrayList<>());

            edgeList.add(to);
            adjList.put(from, edgeList);

            inDegrees[to]++;
        }

        List<Integer> courseOrdering = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < inDegrees.length; i++) {
            if(inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        if(queue.isEmpty()) {
            return new int[]{};
        }

        while(!queue.isEmpty()) {
            int node = queue.poll();

            courseOrdering.add(node);

            List<Integer> edges = adjList.get(node);

            if(edges != null) {
                for(int toNode : edges) {
                    inDegrees[toNode]--;
                    if(inDegrees[toNode] == 0) {
                        queue.add(toNode);
                    }
                }
            }

            adjList.remove(node);
        }

        if(courseOrdering.size() == numCourses) {
            int[] ans = new int[courseOrdering.size()];
            int i = 0;
            for(int course : courseOrdering) {
                ans[i++] = course;
            }
            return ans;
        } else {
            return new int[]{};
        }

    }
}
