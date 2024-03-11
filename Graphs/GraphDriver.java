package Graphs;

import java.util.List;

public class GraphDriver {

    TopologicalSort topoSort;
    BfsDfs bd;

    public GraphDriver() {
        topoSort = new TopologicalSort();
        bd = new BfsDfs();
    }

    public int[] courseSchedulingTwo(int[][] prerequisites, int numCourses) {



        return topoSort.courseScheduleTwo(prerequisites, numCourses);
    }

    public List<List<Integer>> allPathsSrcTarget(int[][] graph) {
        return bd.allPathsSourceTarget(graph);
    }



}
