import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        PrintWriter pw = new PrintWriter(System.out);
        int n = fs.nextInt();
        int m = fs.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int from = fs.nextZeroBasedInt();
            int to = fs.nextZeroBasedInt();
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        int[] colors = fs.nextZeroBasedIntegers(n);
        int targetColor = fs.nextZeroBasedInt();
        fs.close();
        ArrayList<Integer> origins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (colors[i] == targetColor) origins.add(i);
        }
        if (origins.size() == 1) {
            pw.println(-1);
            pw.close();
            return;
        }
        int[] distancesFromNearestOrigins = new int[n];
        Arrays.fill(distancesFromNearestOrigins, -1);
        int[] originsForEachNode = new int[n];
        Arrays.fill(originsForEachNode, -1);
        for (int origin : origins) {
            distancesFromNearestOrigins[origin] = 0;
            originsForEachNode[origin] = origin;
        }
        ArrayDeque<Integer> nodesToVisit = new ArrayDeque<>(origins);
        while (!nodesToVisit.isEmpty()) {
            int currentNode = nodesToVisit.removeFirst();
            for (int adjacentNode : graph.get(currentNode)) {
                if (distancesFromNearestOrigins[adjacentNode] == -1) {
                    distancesFromNearestOrigins[adjacentNode] = distancesFromNearestOrigins[currentNode] + 1;
                    originsForEachNode[adjacentNode] = originsForEachNode[currentNode];
                    nodesToVisit.add(adjacentNode);
                } else if (originsForEachNode[adjacentNode] != originsForEachNode[currentNode]) {
                    pw.println(distancesFromNearestOrigins[adjacentNode] + distancesFromNearestOrigins[currentNode] + 1);
                    pw.close();
                    return;
                }
            }
        }
        pw.println(-1);
        pw.close();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        private String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int nextZeroBasedInt() {
            return Integer.parseInt(next()) - 1;
        }

        int[] nextZeroBasedIntegers(int n) {
            int[] xs = new int[n];
            for (int i = 0; i < n; i++) xs[i] = nextZeroBasedInt();
            return xs;
        }

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
