class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(4);

    System.out.println(g1);

    g1.addEdge(0, 1, 3);

    System.out.println(g1);
  }
}