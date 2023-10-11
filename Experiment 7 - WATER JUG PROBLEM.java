import java.util.*;

class Pair {
    int j1, j2;
    List<Pair> path;

    Pair(int j1, int j2) {
        this.j1 = j1;
        this.j2 = j2;
        path = new ArrayList<>();
    }

    Pair(int j1, int j2, List<Pair> _path) {
        this.j1 = j1;
        this.j2 = j2;

        path = new ArrayList<>();
        path.addAll(_path);
        path.add(new Pair(this.j1, this.j2));
    }
}

public class Exp7 {
    public static void main(String[] args) {
        int jug1 = 5;
        int jug2 = 3;
        int target = 4;

        getPathIfPossible(jug1, jug2, target);
    }

    private static void getPathIfPossible(int jug1, int jug2, int target) {
        boolean[][] visited = new boolean[jug1 + 1][jug2 + 1];
        Queue<Pair> queue = new LinkedList<>();

        // at initial both jugs are empty
        Pair initialState = new Pair(0, 0);
        initialState.path.add(new Pair(0, 0));
        queue.offer(initialState);

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            // skip already visited states and overflowing water states
            if (curr.j1 > jug1 || curr.j2 > jug2 || visited[curr.j1][curr.j2])
                continue;

            // mark present jugs state as visited
            visited[curr.j1][curr.j2] = true;

            if (curr.j1 == target || curr.j2 == target) {
                if (curr.j1 == target) {
                    // if jug1 holds required amount of water empty jug2 and push it into our path.
                    curr.path.add(new Pair(curr.j1, 0));
                } else {
                    // else if jug2 holds required amount of water empty jug1 and push it into our
                    // path.
                    curr.path.add(new Pair(0, curr.j2));
                }
                int n = curr.path.size();
                System.out.println(
                        "path of states of jugs followed is -");
                for (int i = 0; i < n; i++)
                    System.out.println(curr.path.get(i).j1 + " , " + curr.path.get(i).j2);
                return;
            }

            // if target not yet found, then we have the following cases ~
            // fill jug and empty other
            queue.offer(new Pair(jug1, 0, curr.path));
            queue.offer(new Pair(0, jug2, curr.path));

            // fill jug and other remain untouched
            queue.offer(new Pair(jug1, curr.j2, curr.path));
            queue.offer(new Pair(curr.j1, jug2, curr.path));

            // empty jug and let other remain untouched
            queue.offer(new Pair(0, curr.j2, curr.path));
            queue.offer(new Pair(curr.j1, 0, curr.path));

            // transfer water from one to another until one jug becomes empty
            // or until one jug becomes full

            // transferring water from jug1 to jug2
            int emptyJug = jug2 - curr.j2;
            int amountTransferred = Math.min(curr.j1, emptyJug);
            int j2 = curr.j2 + amountTransferred;
            int j1 = curr.j1 - amountTransferred;
            queue.offer(new Pair(j1, j2, curr.path));

            // tranferring water from jug2 to jug1
            emptyJug = jug1 - curr.j1;
            amountTransferred = Math.min(curr.j2, emptyJug);
            j2 = curr.j2 - amountTransferred;
            j1 = curr.j1 + amountTransferred;
            queue.offer(new Pair(j1, j2, curr.path));
        }

        System.out.println("not possible to obtain target :(");
    }
}