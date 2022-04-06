package course.java.algorithms.dijkstra;

public class Dijkstra {

    public static void dijkstra(int[][] graph, int startNode) {
        int count = graph.length;
        boolean[] visited = new boolean[count];
        int[] distance = new int[count];
        for (int i = 0; i < count; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        // Distance to the start node is 0
        distance[startNode] = 0;
        for (int i = 0; i < count; i++) {

            // find the unvisited vertex with minimum distance from the start
            int u = findMinDistance(distance, visited);
            visited[u] = true;

            // Update minimum distances between  next unvisited (front) vertices and the start vertex
            for (int v = 0; v < count; v++) {
                if (!visited[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        for (int i = 0; i < distance.length; i++) {
            System.out.println(String.format("Distance from %s to %s is %s", startNode, i, distance[i]));
        }

    }

    // Finding the unvisited vertex with minimum distance from the start and return its index
    private static int findMinDistance(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int myGraph[][] = new int[][] {
                { 0, 0, 1, 3, 0, 0, 0 },
                { 0, 0, 2, 0, 0, 4, 0 },
                { 1, 2, 0, 1, 5, 0, 0 },
                { 2, 0, 1, 0, 2, 0, 1 },
                { 0, 0, 3, 2, 0, 3, 0 },
                { 0, 3, 0, 0, 2, 0, 2 },
                { 0, 0, 0, 1, 0, 1, 0 } };
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra(myGraph, 0);
    }
}
