import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphConnectedComponents {
    private static void dfs(int node, boolean[] visited, Map<Integer, List<Integer>> graph) {
        visited[node] = true;
        List<Integer> neighbors = graph.get(node);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, graph);
                }
            }
        }
    }

    private static int countComponents(Map<Integer, List<Integer>> graph) {
        int vertices = graph.size();
        boolean[] visited = new boolean[vertices];
        int components = 0;

        for (int vertex : graph.keySet()) {
            if (!visited[vertex]) {
                dfs(vertex, visited, graph);
                components++;
            }
        }

        return components;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1}, {0, 4}, {0, 8},
                {1, 6}, {1, 9},
                {2, 3}, {2, 7},
                {3, 5}, {3, 7},
                {4, 8}, {4, 9},
                {5, 7},
                {6, 8},
                {8, 9}
        };

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }

        int numComponents = countComponents(graph);

        System.out.println("Число компонент связности: " + numComponents);
    }
}