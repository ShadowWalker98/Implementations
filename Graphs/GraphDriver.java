package Graphs;

import java.util.List;

public class GraphDriver {

    TopologicalSort topoSort;
    BfsDfs bd;
    ShortestPath sp;

    public GraphDriver() {
        topoSort = new TopologicalSort();
        bd = new BfsDfs();
        sp = new ShortestPath();
    }

    public int[] courseSchedulingTwo(int[][] prerequisites, int numCourses) {



        return topoSort.courseScheduleTwo(prerequisites, numCourses);
    }

    public List<List<Integer>> allPathsSrcTarget(int[][] graph) {
        return bd.allPathsSourceTarget(graph);
    }

    public void dijkstras(int[][] graph, int src) {
        sp.dijkstras(graph, src);
    }

    public int networkDelay(int[][] times, int n, int k) {
        return sp.networkDelay(times, n, k);
    }

    public int findCheapestFlights(int n, int[][] flights, int src, int dst, int k) {
        return sp.findCheapestFlights(n, flights, src, dst, k);
    }

}
