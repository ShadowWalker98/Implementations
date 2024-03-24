package Graphs;

import java.util.*;
import java.util.List;

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

    public int minCost(int[][] grid) {
        // 1 means cell will go to the right grid[i][j + 1]
        // 2 means left grid[i][j - 1]
        // 3 means down grid[i + 1][j]
        // 4 means up grid[i - 1][j]
        // we will use the directions method to check which direction to go in
        // lets set up the explored and the cost matrix first

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] cost = new int[grid.length][grid[0].length];
        boolean[][] explored = new boolean[grid.length][grid[0].length];

        // let's fill the cost matrix with Integer.MAX_VALUE because at this moment we don't know
        // which cells we can reach

        for(int i = 0; i < cost.length; i++) {
            for(int j = 0; j < cost[i].length; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        // setting the cost of reaching (0,0) to 0 as we start from that cell.
        cost[0][0] = 0;

        // setting up the priority queue for selecting the next best node

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // what do we put in the pq?
        // we put in the node we want to traverse to and the cost associated with reaching that node
        // this cost is also saved in the cost matrix
        // the int[]{row, col, cost}

        pq.add(new int[]{0, 0, 0});

        while(!pq.isEmpty()) {
            int[] node = pq.poll();

            int row = node[0];
            int col = node[1];
            int wt = node[2];

            if(row == rows - 1 && col == cols - 1) {
                return cost[row][col];
            }

            if(explored[row][col]) continue;

            // set it to explored
            explored[row][col] = true;

            // let's first look at the place we can reach without changing the direction associated with the node
            int[] newDirection = directions(row, col, grid[row][col]);
            int nr = newDirection[0];
            int nc = newDirection[1];
            if(nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                // check if this node still hasn't been explored
                // and if we go to this node will the cost be lowered than before?
                if(!explored[nr][nc] && cost[row][col] < cost[nr][nc]) {
                    // we update the cost if it is better
                    cost[nr][nc] = cost[row][col];
                    // add it to the pq so that we can process it later
                    pq.add(new int[]{nr, nc, cost[nr][nc]});
                }
            }

            // then we check the nodes which we can reach if we change the directions,
            // provided the cost is minimised.

            for(int i = 1; i <= 4; i++) {
                if(grid[row][col] != i) {
                    int[] newDir = directions(row, col, i);
                    int nrow = newDir[0];
                    int ncol = newDir[1];
                    // check if this new direction is valid or not
                    if(nrow >= 0 && nrow < rows && ncol >= 0 && ncol < cols) {
                        if(!explored[nrow][ncol] && (cost[row][col] + 1 < cost[nrow][ncol])) {
                            // we update the cost if it is better
                            cost[nrow][ncol] = cost[row][col] + 1;
                            // add it to the pq so that we can process it later
                            pq.add(new int[]{nrow, ncol, cost[nrow][ncol]});
                        }
                    }
                }
            }
        }

        return cost[rows - 1][cols - 1];
    }

    private int[] directions(int r, int c, int d) {
        if(d == 1) {
            return new int[]{r, c + 1};
        } else if(d == 2) {
            return new int[]{r, c - 1};
        } else if(d == 3) {
            return new int[]{r + 1, c};
        } else {
            return new int[]{r - 1, c};
        }
    }

    public int findCheapestFlights(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int[] i : flights) {
            adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[]{i[1], i[2]});
        }

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src, 0});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int dist = temp[0];
            int node = temp[1];
            int steps = temp[2];

            if(steps > stops[node] || steps > k + 1) {
                continue;
            }

            stops[node] = steps;
            if(node == dst) {
                return dist;
            }
            if(!adj.containsKey(node)) continue;
            for(int[] a : adj.get(node)) {
                pq.offer(new int[]{a[1] + dist, a[0], steps + 1});
            }
        }
        return -1;
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

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, List<int[]>> adjList = new HashMap<>();

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int wt = edge[2];

            adjList.computeIfAbsent(from, k -> new ArrayList<int[]>()).add(new int[]{to, wt});
            adjList.computeIfAbsent(to, k -> new ArrayList<int[]>()).add(new int[]{from, wt});
        }

        int ans = n;
        int minCityId = -1;

        for(int i = 0; i < n; i++) {
            int minCities = dijkstras(adjList, n, distanceThreshold, i);
            if(minCities <= ans) {
                ans = minCities;
                minCityId = i;
            }
        }
        return minCityId;
    }

    public int dijkstras(Map<Integer, List<int[]>> graph, int n, int distanceThreshold, int src) {
        int[] dist = new int[n];
        boolean[] explored = new boolean[n];

        int numCities = 0;

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        dist[src] = 0;

        pq.add(new int[] {src, 0});

        while(!pq.isEmpty()) {
            int[] node = pq.poll();

            int nodeId = node[0];
            int nodeDist = node[1];

            List<int[]> edges = graph.get(nodeId);

            if(edges != null) {
                for(int[] edge : edges) {
                    int to = edge[0];
                    int wt = edge[1];

                    if(dist[nodeId] + wt < dist[to]) {
                        if(dist[to] > distanceThreshold && dist[nodeId] + wt <= distanceThreshold) {
                            numCities++;
                        }
                        dist[to] = dist[nodeId] + wt;
                        pq.add(new int[]{to, dist[to]});
                    }
                }
            }
        }
        return numCities;
    }

    public int[] BellmanFord(Map<Integer, List<int[]>> graph, int src, int n) {
        int[] dist = new int[n];
        int[] prev = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n; j++) {
                List<int[]> edges = graph.get(j);
                int from = j;

                if(edges != null) {
                    for(int[] edge: edges) {
                        int to = edge[0];
                        int wt = edge[1];
                        update(new int[]{from, to, wt}, dist);
                    }
                }
            }
        }
        return dist;
    }

    private void update(int[] edge, int[] dist) {
        int from = edge[0];
        int to = edge[1];
        int wt = edge[2];

        if(dist[from] != Integer.MAX_VALUE) {
            dist[to] = Math.min(dist[to], dist[from] + wt);
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int startNode, int endNode) {
        Map<Integer, List<double[]>> adj = new HashMap<>();

        for(int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        // building the graph from the edges.

        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double wt = succProb[i];

            adj.get(from).add(new double[] {to, wt});
            adj.get(to).add(new double[] {from, wt});

        }

        // now implementing modded dijkstras which instead uses a PQ sorted by max probability and
        // this cost has to be maximised instead of minimised.

        // making a double array of the probabilities of the highest probability path
        double[] distProbs = new double[n];
        // setting probability of start node as 1
        distProbs[startNode] = 1;

        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b[1], a[1]);
        });

        pq.add(new double[]{startNode, 1});

        while(!pq.isEmpty()) {
            double[] pair = pq.poll();

            int v = (int) pair[0];

            for(double[] edge : adj.get(v)) {
                double prob = edge[1];
                int to = (int) edge[0];

                if(distProbs[v] * prob > distProbs[to]) {
                    distProbs[to] = distProbs[v] * prob;
                    pq.add(new double[] {to, distProbs[to]});
                }
            }
        }
        return distProbs[endNode];
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<Integer, List<EDPair>> adj = new HashMap<>();
        Map<String, Integer> charMap = new HashMap<>();

        int count = 0;

        for(int i = 0; i < equations.size(); i++) {
            String from = equations.get(i).get(0);
            if(!charMap.containsKey(from)) {
                charMap.put(from, count);
                count++;
            }
            String to = equations.get(i).get(1);
            if(!charMap.containsKey(to)) {
                charMap.put(to, count);
                count++;
            }
            double wt = values[i];

            List<EDPair> edgesList1 = adj.getOrDefault(charMap.get(from), new ArrayList<>());
            edgesList1.add(new EDPair(charMap.get(to), wt));
            adj.put(charMap.get(from), edgesList1);

            List<EDPair> edgesList2 = adj.getOrDefault(charMap.get(to), new ArrayList<>());
            edgesList2.add(new EDPair(charMap.get(from), 1 / wt));
            adj.put(charMap.get(to), edgesList2);
        }

        double[] answers = new double[queries.size()];
        boolean[] visited = new boolean[adj.size()];
        int track = 0;
        for(List<String> query : queries) {
            Arrays.fill(visited, false);
            if(charMap.containsKey(query.get(0)) && charMap.containsKey(query.get(1))) {
                int nr = charMap.get(query.get(0));
                int dr = charMap.get(query.get(1));
                answers[track] = dfs(nr, dr, visited, adj, 1);
            } else {
                answers[track] = -1;
            }
            track++;
        }

        return answers;
    }

    private double dfs(int current, int dest, boolean[] visited, Map<Integer, List<EDPair>> adj, double product) {

        visited[current] = true;

        if(current == dest) {
            return product;
        }

        for(EDPair edge : adj.get(current)) {
            int to = edge.to;
            double wt = edge.wt;
            if(!visited[to]) {
                double res = dfs(to, dest, visited, adj, product * wt);
                if(res != -1) {
                    return res;
                }
            }
        }

        return -1;
    }

    class EDPair {
        int to;
        double wt;

        public EDPair(int to, double wt) {
            this.to = to;
            this.wt = wt;
        }
    }

    public int shortestPathLength(int[][] graph) {
        if (graph.length == 1) {
            return 0;
        }

        int n = graph.length;
        int endingMask = (1 << n) - 1;
        boolean[][] seen = new boolean[n][endingMask];
        ArrayList<int[]> queue = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            queue.add(new int[] {i, 1 << i});
            seen[i][1 << i] = true;
        }

        int steps = 0;
        while (!queue.isEmpty()) {
            ArrayList<int[]> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int[] currentPair = queue.get(i);
                int node = currentPair[0];
                int mask = currentPair[1];
                for (int neighbor : graph[node]) {
                    int nextMask = mask | (1 << neighbor);
                    if (nextMask == endingMask) {
                        return 1 + steps;
                    }

                    if (!seen[neighbor][nextMask]) {
                        seen[neighbor][nextMask] = true;
                        nextQueue.add(new int[] {neighbor, nextMask});
                    }
                }
            }
            steps++;
            queue = nextQueue;
        }

        return -1;
    }

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int wt = edge[2] + 1;

            List<int[]> edgeList1 = adj.getOrDefault(from, new ArrayList<>());
            edgeList1.add(new int[] {to, wt});

            List<int[]> edgeList2 = adj.getOrDefault(to, new ArrayList<>());
            edgeList2.add(new int[]{from , wt});
        }
        int[] dist = new int[n];
        boolean[] explored = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});

        while(!pq.isEmpty()) {
            int[] pair = pq.poll();
            int v = pair[0];

            if(explored[v]) continue;
            else explored[v] = true;

            List<int[]> edgeList = adj.get(v);
            if(edgeList != null) {
                for(int[] edge : edgeList) {
                    int to = edge[0];
                    int wt = edge[1];
                    if(dist[v] + wt < dist[to]) {
                        dist[to] = dist[v] + wt;
                        pq.add(new int[]{to, dist[to]});
                    }
                }
            }
        }

        int ans = 0;

        for(int distance : dist) {
            if(distance <= maxMoves) {
                ans += 1;
            }
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if(dist[u] > maxMoves && dist[v] > maxMoves) {
                continue;
            }

            int c1 = Math.max(0, maxMoves - dist[u]);
            int c2 = Math.max(0, maxMoves - dist[v]);

            ans += Math.min(c1 + c2, w);
        }
        return ans;
    }


    public int countPaths(int n, int[][] roads) {
        int mod = (int) 1e9 + 7;

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for(int i = 0; i < roads.length; i++) {
            int[] road = roads[i];

            int u = road[0];
            int v = road[1];
            int w = road[2];

            List<int[]> edgeList1 = graph.getOrDefault(u, new ArrayList<>());
            edgeList1.add(new int[] {v, w});
            graph.put(u, edgeList1);
            List<int[]> edgeList2 = graph.getOrDefault(v, new ArrayList<>());
            edgeList2.add(new int[] {u, w});
            graph.put(v, edgeList2);
        }

        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);

        int src = 0;
        int dst = n - 1;
        dist[src] = 0;
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {src, 0});

        while(!pq.isEmpty()) {
            int[] nodeDetes = pq.poll();
            int node = nodeDetes[0];

            List<int[]> edges = graph.get(node);

            if(edges != null) {
                for(int[] edge : edges) {
                    int to = edge[0];
                    int wt = edge[1];

                    if(dist[node] + wt < dist[to] ) {
                        if(to == dst) {
                            ans = 1;
                        }
                        dist[to] = dist[node] + wt;
                        pq.add(new int[] {to, dist[to]});
                    } else if(to == dst && dist[node] + wt == dist[to]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }


}
