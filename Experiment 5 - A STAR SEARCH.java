import java.util.*;

class GraphNode {
    public final String name;
    public double g_scores;
    public double f_scores = Double.POSITIVE_INFINITY;
    public List<Edge> adjacenciesList;
    public GraphNode parent;

    public GraphNode(String name) {
        this.name = name;
        adjacenciesList = new LinkedList<>();
    }

    public String toString() {
        return name;
    }
}

class Edge {
    public final double cost;
    public final GraphNode target;

    public Edge(GraphNode targetNode, double costVal) {
        target = targetNode;
        cost = costVal;
    }
}

public class Exp5 {
    static HashMap<String, Double> heuristicMap = new HashMap<>();

    public static void main(String[] args) {
        heuristicMap.put("A", 11.0);
        heuristicMap.put("B", 6.0);
        heuristicMap.put("C", 99.0);
        heuristicMap.put("D", 1.0);
        heuristicMap.put("E", 7.0);
        heuristicMap.put("G", 0.0);

        Scanner sc = new Scanner(System.in);

        System.out.println("enter start node:");
        String startNode = sc.nextLine();

        System.out.println("enter end node:");
        String endNode = sc.nextLine();

        GraphNode n1 = new GraphNode("A");
        GraphNode n2 = new GraphNode("B");
        GraphNode n3 = new GraphNode("C");
        GraphNode n4 = new GraphNode("D");
        GraphNode n5 = new GraphNode("E");
        GraphNode n6 = new GraphNode("G");

        n1.adjacenciesList = new LinkedList<>(Arrays.asList(new Edge(n2, 2), new Edge(n5, 3)));
        n2.adjacenciesList = new LinkedList<>(Arrays.asList(new Edge(n3, 1), new Edge(n6, 9)));
        n5.adjacenciesList = new LinkedList<>(Collections.singletonList(new Edge(n4, 6)));
        n4.adjacenciesList = new LinkedList<>(Collections.singletonList(new Edge(n6, 1)));

        GraphNode start = null, end = null;

        for (GraphNode node : Arrays.asList(n1, n2, n3, n4, n5, n6)) {
            if (node.name.equals(startNode)) {
                start = node;
            }

            if (node.name.equals(endNode)) {
                end = node;
            }
        }

        if (start == null || end == null) {
            System.out.println("start/end node does not exist!");
        }

        aStarSearch(start, end);
        sc.close();
    }

    public static void aStarSearch(GraphNode source, GraphNode goal) {
        source.f_scores = heuristicMap.get(source.name);
        Set<GraphNode> explored = new HashSet<>();

        PriorityQueue<GraphNode> queue = new PriorityQueue<>(new Comparator<GraphNode>() {
            public int compare(GraphNode i, GraphNode j) {
                return (int) (i.f_scores - j.f_scores);
            }
        });

        source.g_scores = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            GraphNode current = queue.poll();
            explored.add(current);

            if (current.equals(goal)) {
                printPath(current, source);
                return;
            }

            for (Edge e : current.adjacenciesList) {
                GraphNode child = e.target;
                double cost = e.cost;
                double temp_g_scores = current.g_scores + cost;

                if (!queue.contains(child) || temp_g_scores < child.g_scores) {
                    child.parent = current;
                    child.g_scores = temp_g_scores;
                    child.f_scores = temp_g_scores + heuristicMap.get(child.name);

                    if (queue.contains(child)) {
                        queue.remove(child);
                    }

                    queue.add(child);
                }
            }
        }

        System.out.println("path does not exist.");
    }

    public static void printPath(GraphNode target, GraphNode source) {
        System.out.println("path exists and is ~");
        List<GraphNode> path = new ArrayList<>();

        for (GraphNode node = target; node != null; node = node.parent) {
            path.add(node);
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i).name + " ");
        }

        System.out.println("\nPath cost: " + target.g_scores);
    }
}
