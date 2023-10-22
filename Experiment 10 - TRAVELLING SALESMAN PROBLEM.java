import java.util.*;

public class Exp10 {

    static int N = 4;

    // stores the final solution, path of the salesman
    static int final_path[] = new int[N + 1];

    // keeps track of the already visited nodes in a particular path
    static boolean visited[] = new boolean[N];

    // stores the final minimum weight of shortest tour
    static int final_res = Integer.MAX_VALUE;

    // to copy temporary solution to the final solution
    static void copyToFinal(int curr_path[]) {
        for (int i = 0; i < N; i++)
            final_path[i] = curr_path[i];
        final_path[N] = curr_path[0];
    }

    // to find the minimum edge cost
    // end at the vertex i
    static int firstMin(int adj[][], int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++)
            if (adj[i][k] < min && i != k)
                min = adj[i][k];
        return min;
    }

    // to find the second minimum edge cost
    static int secondMin(int adj[][], int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j)
                continue;

            if (adj[i][j] <= first) {
                second = first;
                first = adj[i][j];
            } else if (adj[i][j] <= second &&
                    adj[i][j] != first)
                second = adj[i][j];
        }
        return second;
    }

    // curr_bound -> lower bound of the root node
    // curr_weight-> stores the weight of the path so far
    // level-> current level while moving in the search space tree
    // curr_path[] -> where the solution is being stored which would later be copied
    // to final_path[]

    static void TSPRec(int adj[][], int curr_bound, int curr_weight,
            int level, int curr_path[]) {
        if (level == N) {
            // to check if there is an edge from last vertex in path back to first vertex
            if (adj[curr_path[level - 1]][curr_path[0]] != 0) {
                int curr_res = curr_weight +
                        adj[curr_path[level - 1]][curr_path[0]];

                if (curr_res < final_res) {
                    copyToFinal(curr_path);
                    final_res = curr_res;
                }
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            // next vertex if it is not same
            if (adj[curr_path[level - 1]][i] != 0 &&
                    visited[i] == false) {
                int temp = curr_bound;
                curr_weight += adj[curr_path[level - 1]][i];

                // different computation of curr_bound for level 2 from other levels
                if (level == 1)
                    curr_bound -= ((firstMin(adj, curr_path[level - 1]) +
                            firstMin(adj, i)) / 2);
                else
                    curr_bound -= ((secondMin(adj, curr_path[level - 1]) +
                            firstMin(adj, i)) / 2);

                // curr_bound + curr_weight is the actual lower bound for the node we arrived on
                // if current lower bound < final_res, we need to explore the node further
                if (curr_bound + curr_weight < final_res) {
                    curr_path[level] = i;
                    visited[i] = true;

                    // call TSPRec for the next level
                    TSPRec(adj, curr_bound, curr_weight, level + 1,
                            curr_path);
                }

                // else we prune the node by resetting all changes to curr_weight and curr_bound
                curr_weight -= adj[curr_path[level - 1]][i];
                curr_bound = temp;

                // Also reset the visited array
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++)
                    visited[curr_path[j]] = true;
            }
        }
    }

    static void TSP(int adj[][]) {
        int curr_path[] = new int[N + 1];

        // to calculate initial lower bound for the root node
        // 1/2 * (sum of first min + second min)

        int curr_bound = 0;
        Arrays.fill(curr_path, -1);
        Arrays.fill(visited, false);

        // to compute initial bound
        for (int i = 0; i < N; i++)
            curr_bound += (firstMin(adj, i) +
                    secondMin(adj, i));

        // to round off the lower bound to an integer
        curr_bound = (curr_bound == 1) ? curr_bound / 2 + 1 : curr_bound / 2;

        // start at vertex 1, first vertex in curr_path[] is 0
        visited[0] = true;
        curr_path[0] = 0;

        TSPRec(adj, curr_bound, 0, 1, curr_path);
    }

    public static void main(String[] args) {
        // adjacency matrix
        int adj[][] = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 },
        };

        TSP(adj);

        System.out.println("minimum cost => \n" + final_res);
        System.out.println("Path Taken ~ ");
        for (int i = 0; i <= N; i++) {
            System.out.println(final_path[i]);
        }
    }
}
