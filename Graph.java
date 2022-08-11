public class Graph {
  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public Graph(int numNodes) {
    this.countNodes = numNodes;
    this.countEdges = 0;
    this.adjMatrix = new int[numNodes][numNodes];
  }

  public void addEdge(int source, int sink, int weight) {
    if (source < 0 || source >= this.adjMatrix.length || sink < 0 || sink >= this.adjMatrix.length || weight <= 0) {
      System.err.println("Invalid edge!\nsource: " + source + "\nsink: " + sink + "\nweight: " + weight);

      return;
    }

    this.countEdges++;
    this.adjMatrix[source][sink] = weight;
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
}