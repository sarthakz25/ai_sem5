import java.util.*;

public class Exp4 {
    private LinkedList<Integer> adj[];

    Exp4(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int s) {
        boolean visited[] = new boolean[adj.length];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.println(s + " ");
            Iterator<Integer> i = adj[s].listIterator();

            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.println(v + " ");
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean visited[] = new boolean[adj.length];
        DFSUtil(v, visited);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices: ");
        int v = sc.nextInt();
        Exp4 exp = new Exp4(v);

        System.out.println("Enter the number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter the edges: ");
        for (int i = 0; i < e; i++) {
            exp.addEdge(sc.nextInt(), sc.nextInt());
        }

        System.out.println("Enter the starting vertex for BFS: ");
        exp.BFS(sc.nextInt());

        System.out.println("Enter the starting vertex for DFS: ");
        exp.DFS(sc.nextInt());

        sc.close();
    }
}
