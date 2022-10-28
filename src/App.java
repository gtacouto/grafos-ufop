import java.io.IOException;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe a tarefa:");
        System.out.println("1- Caminho Minimo");
        System.out.println("2- Labirinto");
        System.out.println("3- Sair");
        int option = sc.nextInt();
        while (option != 3) {
            switch (option) {
                case 1:
                    System.out.println("Bellman - Ford:");
                    GraphList g1 = new GraphList("files/cm/toy.txt");
                    System.out.println("Processando ...");
                    long startTime = System.currentTimeMillis();
                    g1.bellmanford(0, g1.getCountNodes(), 1);
                    float totalTime = System.currentTimeMillis() - startTime;
                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");

                    System.out.println("Floyd- Warshall:");
                    //g1.floyd_warshall(1);
                    break;

                case 2:
                    System.out.println("Case 2");
                    break;

                case 3:
                    System.out.println("Case 3");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
            System.out.println();
            System.out.println("Informe a tarefa:");
            System.out.println("1- Caminho Minimo");
            System.out.println("2- Labirinto");
            System.out.println("3- Sair");
            option = sc.nextInt();
        }

        // System.out.println(option);
        sc.close();
    }
}