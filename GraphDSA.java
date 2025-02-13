import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class GraphDSA {

    static class Edge {
        int wt;
        int src;
        int dest;

        Edge(int wt, int src, int dest) {
            this.wt = wt;
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createG(ArrayList<Edge> graph[], int V) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0, 0, 2));
        graph[1].add(new Edge(1, 1, 0));
        graph[1].add(new Edge(1, 1, 3));
        graph[2].add(new Edge(2, 2, 1));
        graph[2].add(new Edge(2, 2, 0));
        graph[2].add(new Edge(2, 2, 3));
        graph[3].add(new Edge(3, 3, 0));
        graph[3].add(new Edge(3, 3, 2));
    }

    public static void bfs(ArrayList<Edge> graph[], int V) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[V];

        // Start BFS from node 0
        q.add(0);
        vis[0] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(curr + " ");

            for (Edge e : graph[curr]) {
                if (!vis[e.dest]) {
                    q.add(e.dest);
                    vis[e.dest] = true;
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge> graph[], boolean vis[], int curr) {
        System.out.print(curr + " ");
        vis[curr] = true;
        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                dfs(graph, vis, e.dest);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        System.out.println("Hey");
        int V = 4; // Corrected the number of vertices to match the graph
        ArrayList<Edge> graph[] = new ArrayList[V];
        createG(graph, V); // Initialize the graph

        boolean vis[] = new boolean[V]; // visited array for BFS and DFS

        System.out.print("The BFS is: ");
        bfs(graph, V);
        System.out.println();

        System.out.print("The DFS is: ");
        dfs(graph, vis, 1); // Start DFS from node 0
        System.out.println();
    }
}