// matAdj -> mais rápido, mas ocupa um espaço maior O(n).
// listAdj -> ocupa menos espaço O(v + e).

// Passeio -> qualquer conjunto de nós.
// Trilha -> não é permitido repetir arestas.
// Circuito -> não é permitido repetir arestas, começa e termina no mesmo nó.
// Caminho -> Não pode repetir arestas ou nó.
// Ciclo -> Não pode repetir arestas ou nó, começa e termina no mesmo nó.

// Apresente uma trilha que não é caminho.

// aresta -> ---------
// nó -> (5)

// Apresente um circuito que não é um ciclo.

// Conexidade

// Não orientado
// Conexo -> consigo encontrar caminho para todos os nós.
// Desconexo -> Tem pelo menos um nó que não consigo encontrar caminho para todos os nós.
// aresta ponto | nó de ligação -> remoção aumenta numeros de componentes conexos(conjunto).

// Orientado
// f-conexo -> Existe caminho para todos pares de nós em ambos sentidos
// sf-conexo -> é conexo ao desconsiderar a orientação de arestas.
// desconexo -> é desconexo ao desconsiderar a orientação das arestas

//conectividade de vertices -> minimo de nós q devo remover pra ser um grafo desconexo. k(G) =
//conectividade de arestas -> minimo de arestas q devo remover pra ser um grafo desconexo.λ(G) =

class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(4);

    g1.addEdge(0, 1, 1);
    g1.addEdge(0, 3, 1);
    g1.addEdge(1, 0, 1);
    g1.addEdge(3, 0, 1);

    // aula 1
    System.out.println(g1);
    System.out.println("O grau é: " + g1.degree(0));
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

    Graph g5 = new Graph(8);

    g5.addEdgeUnoriented(4, 5, 1);
    g5.addEdgeUnoriented(4, 0, 1);
    g5.addEdgeUnoriented(4, 3, 1);
    g5.addEdgeUnoriented(0, 6, 1);
    g5.addEdgeUnoriented(6, 1, 1);
    g5.addEdgeUnoriented(3, 2, 1);
    g5.addEdgeUnoriented(3, 7, 1);

    System.out.println("\nGrafo 5:");
    System.out.println(g5);
    // System.out.println(g5.density());
    // System.out.println(g5.oriented());
    System.out.println(g5.busca_largura(4));
  }
}