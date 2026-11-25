import java.util.*;

public class PrimsAlgorithm {

    static class Edge {
        int vertex;
        int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static int prims(ArrayList<ArrayList<Edge>> graph, int V) {

        boolean[] visited = new boolean[V];

        PriorityQueue<Edge> pq =
                new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.add(new Edge(0, 0));

        int totalWeight = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < V) {

            Edge current = pq.poll();

            int u = current.vertex;

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            totalWeight += current.weight;
            edgesUsed++;

            for (Edge edge : graph.get(u)) {

                if (!visited[edge.vertex]) {
                    pq.add(new Edge(edge.vertex, edge.weight));
                }
            }
        }

        return totalWeight;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter edges (source destination weight):");

        for (int i = 0; i < E; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            graph.get(u).add(new Edge(v, weight));
            graph.get(v).add(new Edge(u, weight));
        }

        int result = prims(graph, V);

        System.out.println("Minimum Cost: " + result);

        sc.close();
    }
}
