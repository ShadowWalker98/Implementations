package Graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ShortestPath {

    public ShortestPath() {}

    public Integer[] dijkstras(int[][] grid, int s) {

        Integer[] dist = new Integer[grid.length];
        Integer[] prev = new Integer[grid.length];
        Boolean[] explored = new Boolean[grid.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for(int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = null;
            explored[i] = false;
        }

        dist[s] = 0;

        pq.add(new Pair(s, 0));

        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            int v = p.vertex;
            int wt = p.weight;

            if(explored[v]) {
                continue;
            }

            explored[v] = true;

            for(int i = 0; i < grid[v].length; i++) {
                if(grid[v][i] != Integer.MAX_VALUE) {
                    if(!explored[i]) {
                        if(dist[v] + grid[v][i] < dist[i]) {
                            dist[i] = dist[v] + grid[v][i];
                            prev[i] = v;
                            pq.add(new Pair(i, dist[i]));
                        }
                    }
                }
            }
        }

        return dist;
    }

    public Integer[] dijkstras(Integer[][] grid, int s) {

        Integer[] dist = new Integer[grid.length];
        Integer[] prev = new Integer[grid.length];
        Boolean[] explored = new Boolean[grid.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for(int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = null;
            explored[i] = false;
        }

        dist[s] = 0;

        pq.add(new Pair(s, 0));

        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            int v = p.vertex;
            int wt = p.weight;

            if(explored[v]) {
                continue;
            }

            explored[v] = true;

            for(int i = 0; i < grid[v].length; i++) {
                if(grid[v][i] != Integer.MAX_VALUE) {
                    if(!explored[i]) {
                        if(dist[v] + grid[v][i] < dist[i]) {
                            dist[i] = dist[v] + grid[v][i];
                            prev[i] = v;
                            pq.add(new Pair(i, dist[i]));
                        }
                    }
                }
            }
        }

        return dist;
    }

    public int networkDelay(int[][] times, int n, int k) {
        Integer[][] grid = new Integer[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    grid[i][j] = 0;
                } else {
                    grid[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int src = k - 1;

        for(int[] edge : times) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            int wt = edge[2];

            grid[from][to] = wt;
        }

        Integer[] dist = dijkstras(grid, src);
        System.out.println(Arrays.toString(dist));
        int maxTime = 0;

        for(int time : dist) {
            if(time == Integer.MAX_VALUE) {
                return -1;
            } else {
                maxTime = Math.max(maxTime, time);
            }
        }

        return maxTime;
    }



    private class Pair implements Comparable<Pair>{
        int vertex;
        int weight;

        public Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair p) {
            return this.weight - p.weight;
        }
    }
}
