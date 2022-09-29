import java.io.IOException;

// matAdj -> mais rápido, mas ocupa um espaço maior O(n).
// listAdj -> ocupa menos espaço O(v + e).

// isomorfo -> contem no outro

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

class App {
    public static void main(String[] args) throws IOException {
        GraphList g1 = new GraphList("files/graph5.txt");
        System.out.println(g1.kruskal());
        System.out.println(g1.prim());
        // GraphList g1 = new GraphList(5);
        // g1.addEdge(0, 1, 1);
        // g1.addEdge(0, 2, 1);
        // g1.addEdge(1, 4, 1);
        // g1.addEdge(2, 3, 1);
        // // GraphList g1 = new GraphList("src/graph4.txt");
        // System.out.println(g1);
        // GraphList g2 = g1.complement();
        // System.out.println(g2);
        // System.out.println(g1.subgraph(g2));
        // System.out.println(g1.dfsRec(0));

        // GraphMatrix g1 = new GraphMatrix("src/graph3.txt");
        // g1.floydWarshall(0, 3);

        // Graph g1 = new Graph(10);
        // g1.addEdgeUnoriented(7, 5, 1);
        // g1.addEdgeUnoriented(7, 1, 1);
        // g1.addEdgeUnoriented(7, 2, 1);
        // g1.addEdgeUnoriented(1, 0, 1);
        // g1.addEdgeUnoriented(1, 4, 1);
        // g1.addEdgeUnoriented(2, 3, 1);
        // g1.addEdgeUnoriented(5, 6, 1);
        // g1.addEdgeUnoriented(6, 8, 1);
        // System.out.println(g1.bfs(7));
        // System.out.println(g1.connected());
        // Graph g2 = new Graph("graph1.txt");
        // System.out.println(g2);

        // Graph g1 = new Graph(4);
        // g1.addEdge(0, 1, 1);
        // g1.addEdge(1, 0, 1);
        // g1.addEdge(0, 3, 1);
        // g1.addEdge(3, 0, 1);
        // System.out.println("=== g1 ===");
        // System.out.println(g1);
        // System.out.println("degree(0): " + g1.degree(0)); // 2
        // System.out.println("degree(1): " + g1.degree(1)); // 1
        // System.out.println("degree(2): " + g1.degree(2)); // 0
        // System.out.println("degree(3): " + g1.degree(3)); // 1
        // System.out.println("highestDegree: " + g1.highestDegree()); // 2
        // System.out.println("lowestDegree: " + g1.lowestDegree()); // 0
        // System.out.println("density: " + g1.density());
        // System.out.println("\n=== g1 complement ===");
        // System.out.println(g1.complement());
        // Graph g2 = new Graph(3);
        // g2.addEdge(0, 1, 1);
        // g2.addEdge(1, 0, 1);
        // System.out.println("g2 is subGraph? " + g1.subGraph(g2)); // true
        // Graph g3 = new Graph(4);
        // g3.addEdge(1, 3, 1);
        // g3.addEdge(3, 1, 1);
        // System.out.println("g3 is subGraph? " + g1.subGraph(g3)); // false

    }
}