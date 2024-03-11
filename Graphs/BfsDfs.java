package Graphs;

import java.util.*;

public class BfsDfs {

    public BfsDfs() {}

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        int target = graph.length - 1;

        // now call dfs

        List<List<Integer>> ans = new ArrayList<>();

        dfs(graph, 0, new ArrayList<>(), ans,  target);
        return ans;
    }

    private void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> ans, int target) {
        path.add(node);
        if(node == target) {
            List<Integer> pathToTarget = new ArrayList<>(path);
            ans.add(pathToTarget);
        } else {
            int[] edges = graph[node];


            for(int edge : edges) {
                dfs(graph, edge, path, ans, target);
            }

        }
        path.remove(path.size() - 1);
    }
}

