class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(4);

    System.out.println(g1);

    g1.addEdge(0, 1, 5);
    g1.addEdge(0, 2, 1);
    g1.addEdge(0, 3, 2);
    g1.addEdge(0, 0, 3);
    g1.addEdge(0, 4, 4);

    System.out.println(g1);
    System.out.println("O grau é: " + g1.degree(0));// 2
    System.out.println("\nO maior grau é o: " + g1.highestDegree());
    System.out.println("\nO menor grau é o: " + g1.lowestDegree());
  }
}