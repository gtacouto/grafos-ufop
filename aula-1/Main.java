class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(4);

    g1.addEdge(0, 1, 1);
    g1.addEdge(0, 3, 1);
    g1.addEdge(1, 0, 1);
    g1.addEdge(3, 0, 1);

    // aula 1
    System.out.println(g1);
    System.out.println("O grau é: " + g1.degree(0));// 2
    System.out.println("\nO maior grau é o: " + g1.highestDegree());

    // aula 2
    System.out.println("\nO menor grau é o: " + g1.lowestDegree());
    System.out.println(g1.complement());

    // Graph g2 = new Graph(2);

    // g2.addEdge(0, 1, 1);
    // g2.addEdge(1, 0, 1);

    Graph g2 = new Graph(4);

    g2.addEdge(0, 2, 1);
    g2.addEdge(1, 2, 1);
    g2.addEdge(1, 3, 1);
    g2.addEdge(2, 1, 1);
    g2.addEdge(3, 1, 1);
    g2.addEdge(3, 2, 1);

    System.out.println(g2);
    System.out.println(g1.subgraph(g2));

    Graph g3 = new Graph(4);

    g3.addEdge(0, 3, 1);
    g3.addEdge(3, 0, 1);

    System.out.println(g3);
    System.out.println(g1.subgraph(g3));
    System.out.println(g2.subgraph(g3));

    Graph g4 = new Graph(4);

    g4.addEdge(0, 1, 1);
    g4.addEdge(1, 0, 1);
    g4.addEdge(1, 1, 1);

    System.out.println(g4);
    System.out.println(g1.subgraph(g4));
  }
}