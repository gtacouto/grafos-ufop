import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public Graph(int numNodes) {
    this.countNodes = numNodes;
    this.countEdges = 0;
    this.adjMatrix = new int[numNodes][numNodes];
  }

  public Graph(String fileName) throws IOException {
    File file = new File(fileName);
    FileReader reader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(reader);

    // Read header
    String[] line = bufferedReader.readLine().split(" ");
    this.countNodes = (Integer.parseInt(line[0]));
    int fileLines = (Integer.parseInt(line[1]));

    // Create and fill adjMatrix with read edges
    this.adjMatrix = new int[this.countNodes][this.countNodes];

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
    if (source < 0 || source >= this.adjMatrix.length || sink < 0 || sink >= this.adjMatrix.length || weight <= 0) {
      System.err.println("Invalid edge!\nsource: " + source + "\nsink: " + sink + "\nweight: " + weight);

      return;
    }

    this.countEdges++;
    this.adjMatrix[source][sink] = weight;
  }

  public void addEdgeUnoriented(int source, int sink, int weight) {
    if (source < 0 || source >= this.adjMatrix.length || sink < 0 || sink >= this.adjMatrix.length || weight <= 0) {
      System.err.println("Invalid edge!\nsource: " + source + "\nsink: " + sink + "\nweight: " + weight);

      return;
    }

    this.countEdges += 2;
    this.adjMatrix[source][sink] = weight;
    this.adjMatrix[sink][source] = weight;
  }

  public String toString() {
    String str = "";

    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; j++) {
        str += this.adjMatrix[i][j] + "\t";
      }
      str += "\n";
    }

    return str;
  }

  public int degree(int node) {
    // Retorna o grau do nó node;

    // Retorna a quantidade de pesos dentro daquela linha, exemple:
    // 0 5 4 6 => degree = 3, pois de 4 colunas 3 estão preenchidas(estão != 0)

    int degree = 0;

    for (int j = 0; j < this.adjMatrix[node].length; j++) {
      if (this.adjMatrix[node][j] != 0) {
        degree++;
      }
    }

    return degree;
  }

  public int highestDegree() {
    int hightTest2 = 0;

    for (int i = 0; i < this.adjMatrix.length; i++) {
      if (this.degree(i) > hightTest2) {
        hightTest2 = this.degree(i);
      }
    }

    return hightTest2;
  }

  public int lowestDegree() {
    int hightTest2 = this.adjMatrix.length + 1;

    for (int i = 0; i < this.adjMatrix.length; i++) {
      if (this.degree(i) < hightTest2) {
        hightTest2 = this.degree(i);
      }
    }

    return hightTest2;
  }

  public Graph complement() {
    Graph graph = new Graph(this.countNodes);

    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; j++) {
        if (this.adjMatrix[i][j] == 0 && i != j) {
          graph.addEdge(i, j, 1);
        }
      }
    }

    return graph;
  }

  public boolean subgraph(Graph g2) {
    if (g2.countEdges > this.countEdges || g2.countNodes > this.countNodes) {
      return false;
    }

    for (int i = 0; i < g2.adjMatrix.length; i++) {
      for (int j = 0; j < g2.adjMatrix[i].length; j++) {
        if (g2.adjMatrix[i][j] != 0 && this.adjMatrix[i][j] == 0) {
          return false;
        }
      }
    }

    return true;
  }

  public float density() {
    // E = quantidade de arestas que tem
    // M = maximo de arestas possíveis()
    // E / (M*(M-1))

    float e = this.countEdges;
    float m = this.countNodes;

    return e / (m * (m - 1));
  }

  public boolean oriented() {
    // retorna true se o grafo é orientado

    for (int i = 0; i < this.adjMatrix.length / 2; i++) {
      for (int j = 0; j < this.adjMatrix[i].length / 2; j++) {
        if (this.adjMatrix[i][j] != this.adjMatrix[j][i]) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean verifyFlow(int desc[], int u, int v) {
    if (this.adjMatrix[u][v] != 0 && desc[v] == 0) {
      return true;
    }

    return false;
  }

  public ArrayList<Integer> busca_largura(int origem) {
    int desc[] = new int[this.adjMatrix.length];
    ArrayList<Integer> Q = new ArrayList<Integer>();
    ArrayList<Integer> R = new ArrayList<Integer>();

    Q.add(origem);
    R.add(origem);
    desc[origem] = 1;

    while (Q.size() > 0) {
      int u = Q.get(0);
      Q.remove(0);

      for (int v = 0; v < desc.length; v++) {
        if (this.verifyFlow(desc, u, v)) {
          Q.add(v);
          R.add(v);
          desc[v] = 1;
        }
      }

    }

    return R;
  }

  public boolean connected() {
    return this.busca_largura(0).size() == this.adjMatrix.length;
  }

  public ArrayList<Integer> busca_profundidade(int s) {
    int desc[] = new int[this.adjMatrix.length];
    ArrayList<Integer> S = new ArrayList<Integer>();
    ArrayList<Integer> R = new ArrayList<Integer>();

    S.add(s);
    R.add(s);
    desc[s] = 1;

    while (S.size() > 0) {
      int u = S.get(S.size() - 1);

      boolean in = false;

      for (int v = 0; v < desc.length; v++) {
        if (this.verifyFlow(desc, u, v)) {
          S.add(v);
          R.add(v);
          desc[v] = 1;
          in = true;

          break;
        }
      }

      if (in == false) {
        S.remove(S.size() - 1);
      }

    }

    return R;
  }

  private void dfs_rec_aux(int u, int[] desc, ArrayList<Integer> R) {
    desc[u] = 1;

    R.add(u);

    for (int v = 0; v < desc.length; v++) {
      if (this.verifyFlow(desc, u, v)) {
        dfs_rec_aux(v, desc, R);
      }
    }
  }

  public ArrayList<Integer> dfs_rec(int s) {
    int desc[] = new int[this.adjMatrix.length];
    ArrayList<Integer> R = new ArrayList<Integer>();

    dfs_rec_aux(s, desc, R);

    return R;
  }

  private void ord_top_aux(int u, int[] desc, ArrayList<Integer> R) {
    desc[u] = 1;

    for (int v = 0; v < desc.length; v++) {
      if (this.verifyFlow(desc, u, v)) {
        ord_top_aux(v, desc, R);
      }
    }

    R.add(0, u);
  }

  public ArrayList<Integer> ord_top() {
    int desc[] = new int[this.adjMatrix.length];

    ArrayList<Integer> R = new ArrayList<Integer>();

    for (int v = 0; v < desc.length; v++) {
      if (desc[v] == 0) {
        ord_top_aux(v, desc, R);
      }
    }

    return R;
  }

  // componentes conexos

  // grafos tem ciclos
  // def ciclo(self, s):
  // """Retorna True se o grafo tem ciclo e False caso contrario
  // baseado na busca em largura"""
  // desc = [0 for v in range(self.num_vert)]
  // for s in range(self.num_vert):
  // if desc[s] == 0:
  // Q = [s]
  // R = [s]
  // desc[s] = 1
  // while Q:
  // u = Q.pop(0)
  // for (v, w) in self.lista_adj[u]:
  // if desc[v] == 0:
  // Q.append(v)
  // R.append(v)
  // desc[v] = 1
  // else:
  // return True
  // return False

  // algoritmo caminho minimo
  public void dijkstra(int s) {
    int distancia[] = new int[this.adjMatrix.length];
    int pred[] = new int[this.adjMatrix.length];

    for (int v = 0; v < distancia.length; v++) {
      distancia[v] = null;
      pred[v] = null;
    }

    distancia[s] = 0;

    ArrayList<Integer> Q = new ArrayList<Integer>();

  }
}