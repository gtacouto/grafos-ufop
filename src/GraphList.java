import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphList {

    private int countNodes;
    private int countEdges;
    private ArrayList<ArrayList<Edge>> adjList;
    private ArrayList<Edge> edgeList;
    private static final int INF = 999999;

    public GraphList(int countNodes) {
        this.countNodes = countNodes;
        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
        edgeList = new ArrayList<>();
    }

    public GraphList(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));

        // Create and fill adjList with read edges
        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
        edgeList = new ArrayList<>();
        // Adds one edge at time
        for (int i = 0; i < fileLines; ++i) {
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int sink = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            addEdge(source, sink, weight);
        }
        bufferedReader.close();
        reader.close();
    }

    public void addEdge(int source, int sink, int weight) {
        if (source < 0 || source > this.countNodes - 1
                || sink < 0 || sink > this.countNodes - 1 || weight <= 0) {
            System.err.println("Invalid edge: " + source + sink + weight);
            return;
        }
        adjList.get(source).add(new Edge(source, sink, weight));
        edgeList.add(new Edge(source, sink, weight));
        this.countEdges++;
    }

    public void addEdgeUnoriented(int source, int sink, int weight) {
        addEdge(source, sink, weight);
        addEdge(sink, source, weight);
    }

    public int degree(int u) {
        if (u < 0 || u > this.countNodes - 1)
            System.err.println("Invalid node: " + u);
        return this.adjList.get(u).size();
    }

    public int highestDegree() {
        int highest = 0;
        for (int u = 0; u < this.adjList.size(); ++u) {
            int degreeNodeU = this.degree(u);
            if (highest < degreeNodeU)
                highest = degreeNodeU;
        }
        return highest;
    }

    public int lowestDegree() {
        int lowest = this.countNodes;
        for (int u = 0; u < this.adjList.size(); ++u) {
            int degreeNodeU = this.degree(u);
            if (lowest > degreeNodeU)
                lowest = degreeNodeU;
        }
        return lowest;
    }

    public GraphList complement() {
        GraphList g2 = new GraphList(this.countNodes);
        for (int u = 0; u < this.adjList.size(); ++u) {
            for (int v = 0; v < this.countNodes; ++v) {
                boolean addEdgeUV = true;
                for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                    int v2 = this.adjList.get(u).get(idx).getSink();
                    if (v2 == v) { // Edge (u, v) exists and should not be added
                        addEdgeUV = false;
                        break;
                    }
                }
                if (addEdgeUV && u != v) {
                    g2.addEdge(u, v, 1); // It assumes edges are unweighted
                }
            }
        }
        return g2;
    }

    public float density() {
        return (float) this.countEdges / (this.countNodes * (this.countNodes - 1));
    }

    public boolean subgraph(GraphList g2) {
        if (g2.countNodes > this.countNodes || g2.countEdges > this.countEdges)
            return false;
        for (int u = 0; u < g2.adjList.size(); ++u) {
            boolean foundEdge = false;
            for (int idx = 0; idx < g2.adjList.get(u).size(); ++idx) {
                int v = g2.adjList.get(u).get(idx).getSink();
                // Check if edge (u,v) exists in this graph
                for (int idx2 = 0; idx2 < this.adjList.get(u).size(); ++idx2) {
                    int v2 = this.adjList.get(u).get(idx2).getSink();
                    if (v == v2) { // Edge exists
                        foundEdge = true;
                        break;
                    }
                }
                if (!foundEdge)
                    return false;
            }
        }
        return true;
    }

    public boolean connected() {
        return this.bfs(0).size() == this.countNodes;
    }

    public boolean isOriented() {
        for (int u = 0; u < this.adjList.size(); ++u) {
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                int v = this.adjList.get(u).get(idx).getSink();
                boolean hasEdgeVU = false;
                for (int idx2 = 0; idx2 < this.adjList.get(v).size(); ++idx2) {
                    int u2 = this.adjList.get(v).get(idx2).getSink();
                    if (u == u2) {
                        hasEdgeVU = true;
                        break;
                    }
                }
                if (!hasEdgeVU) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> dfsRec(int s) {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> R = new ArrayList<>();
        dfsRecAux(s, desc, R);
        return R;
    }

    public ArrayList<Integer> bfs(int s) {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> Q = new ArrayList<>();
        Q.add(s);
        ArrayList<Integer> R = new ArrayList<>();
        R.add(s);
        desc[s] = 1;
        // Main loop
        while (Q.size() > 0) {
            int u = Q.remove(0);
            for (int v = 0; v < this.adjList.get(u).get(v).getSink(); ++v) {
                if (this.adjList.get(u).get(v).getSink() != 0) { // Edge (u, v) exists
                    if (desc[v] == 0) {
                        Q.add(v);
                        R.add(v);
                        desc[v] = 1;
                    }
                }
            }
        }
        return R;

    }

    public void dfsRecAux(int u, int[] desc, ArrayList<Integer> R) {
        desc[u] = 1;
        R.add(u);
        for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
            int v = this.adjList.get(u).get(idx).getSink();
            if (desc[v] == 0) {
                dfsRecAux(v, desc, R);
            }
        }
    }

    public ArrayList<Edge> kruskal() {
        ArrayList<Edge> T = new ArrayList<Edge>(this.countNodes - 1);
        int[] F = new int[this.countNodes];
        // makeset(u)
        for (int u = 0; u < this.countNodes; ++u)
            F[u] = u;
        edgeList.sort(null);
        for (int idx = 0; idx < edgeList.size(); ++idx) {
            int u = edgeList.get(idx).getSource();
            int v = edgeList.get(idx).getSink();
            if (F[u] != F[v]) { // findset(u) != findset(v)
                T.add(edgeList.get(idx));
                // Save some iterations if tree is already built
                if (T.size() == countNodes - 1)
                    break;
                // union(u, v)
                int k = F[v];
                for (int i = 0; i < F.length; ++i) {
                    if (F[i] == k) {
                        F[i] = F[u];
                    }
                }
            }
        }
        return T;
    }

    public ArrayList<Edge> prim() {
        ArrayList<Edge> T = new ArrayList<Edge>(this.countNodes - 1);
        int s = 0;
        int[] dist = new int[this.countNodes];
        int[] parent = new int[this.countNodes];
        // Q represents the nodes that were not connected yet
        ArrayList<Integer> Q = new ArrayList<Integer>(this.countNodes);
        for (int u = 0; u < this.countNodes; ++u) {
            dist[u] = INF;
            parent[u] = -1;
            Q.add(u);
        }
        dist[s] = 0;
        while (Q.size() != 0) {
            int u = -1;
            int min = INF;
            for (int idx = 0; idx < Q.size(); ++idx) {
                int i = Q.get(idx);
                if (dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }
            // Node u is gonna be connected
            Q.remove((Integer) u);
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                int v = this.adjList.get(u).get(idx).getSink();
                int w = this.adjList.get(u).get(idx).getWeight();
                if (Q.contains(v) && w < dist[v]) {
                    dist[v] = w;
                    parent[v] = u;
                }
            }
        }
        // Recover the tree from parent array
        for (int u = 0; u < parent.length; ++u) {
            if (parent[u] != -1) {
                T.add(new Edge(u, parent[u], 1));
            }
        }
        return T;
    }

    public int getCountNodes() {
        return countNodes;
    }

    public void setCountNodes(int countNodes) {
        this.countNodes = countNodes;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public void setCountEdges(int countEdges) {
        this.countEdges = countEdges;
    }

    public ArrayList<ArrayList<Edge>> getAdjList() {
        return adjList;
    }

    public void setAdjList(ArrayList<ArrayList<Edge>> adjList) {
        this.adjList = adjList;
    }

    public String toString() {
        String str = "";
        for (int u = 0; u < this.adjList.size(); ++u) {
            str += u + ": ";
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                str += this.adjList.get(u).get(idx) + "\t";
            }
            str += "\n";
        }
        return str;
    }

    public int findSmaller(int dist[], boolean visit[]) {

        int smaller = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!visit[i] && (smaller == -1 || dist[i] < dist[smaller])) {
                smaller = i;
            }
        }

        return smaller;
    }

    public void dijkstra(int s, int d) {
        int u;

        int minimo = this.INF;

        ArrayList<Integer> dist = new ArrayList<>();
        ArrayList<Integer> Q = new ArrayList<>();
        ArrayList<Integer> pred = new ArrayList<>();

        for (int i = 0; i < this.countNodes; i++) {
            Q.add(i);
            dist.add((int) this.INF);
            pred.add(-1);
        }

        dist.set(s, 0);

        while (Q.size() > 0) {
            minimo = this.INF;

            u = Q.get(0);

            for (int i : Q) {
                if (dist.get(i) < minimo) {
                    minimo = dist.get(i);
                    u = i;
                }
            }

            Q.remove(Integer.valueOf(u));

            for (Edge edge : adjList.get(u)) {
                if (dist.get(edge.getSink()) > dist.get(u) + edge.getWeight()) {
                    dist.set(edge.getSink(), dist.get(u) + edge.getWeight());
                    pred.set(edge.getSink(), u);
                }
            }
        }
    }

    void path(int parent[], int vertex, List<Integer> path) {
        if (vertex < 0) {
            return;
        }

        path(parent, parent[vertex], path);
        path.add(vertex);
    }

    public void bellmanford(int source, int n, int destiny) {
        int dist[] = new int[n];
        int parent[] = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = 999999;
        }

        dist[source] = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : this.edgeList) {
                int u = edge.getSource();
                int v = edge.getSink();
                int w = edge.getWeight();

                if (dist[u] != 999999 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;

                    parent[v] = u;
                }
            }
        }

        for (Edge edge : this.edgeList) {
            int u = edge.getSource();
            int v = edge.getSink();
            int w = edge.getWeight();

            if (dist[u] != 999999 && dist[u] + w < dist[v]) {
                System.out.println("Negative-weight cycle is found!!");
                return;
            }
        }

        if (destiny != source && dist[destiny] < 999999) {
            List<Integer> path = new ArrayList<>();
            path(parent, destiny, path);
            System.out.println("Caminho: " + path);

            System.out.println("Custo: " + dist[destiny]);
        }
    }

    public void floyd_warshall(int tam) {
        int n = this.adjList.size();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int smaller = Math.min(
                            this.adjList.get(i).get(k).getWeight() + this.adjList.get(k).get(j).getWeight(),
                            this.adjList.get(i).get(j).getWeight());

                    this.adjList.get(i).set(i, new Edge(i, k, smaller));
                }
            }
        }
    }

    public void bellmanford_melhorado(int s, int d) {

        int Inf = 9999999;
        ArrayList<Integer> dist = new ArrayList<>();
        ArrayList<Integer> pred = new ArrayList<>();
        for (int i = 0; i < this.countNodes; i++) {
            dist.add((int) Inf);
            pred.add(-1);
        }
        dist.set(s, 0);
        boolean trocou = false;

        for (int i = 0; i < this.countNodes; i++) {
            trocou = false;
            for (int j = 0; j < this.countEdges; j++) {
                if (dist.get(edgeList.get(j).getSink()) > dist.get(edgeList.get(j).getSource())
                        + edgeList.get(j).getWeight()) {
                    dist.set(edgeList.get(j).getSink(),
                            dist.get(edgeList.get(j).getSource()) + edgeList.get(j).getWeight());
                    pred.set(edgeList.get(j).getSink(), edgeList.get(j).getSource());
                    trocou = true;
                }
            }
            if (trocou == false) {
                break;
            }

        }

    }

}
