import java.util.Arrays;

public class Grafo {
    int vertices, aristas;
    Arista[] listaAristas;

    public Grafo(int vertices, int aristas) {
        this.vertices = vertices;
        this.aristas = aristas;
        listaAristas = new Arista[aristas];
        for (int i = 0; i < aristas; i++) {
            listaAristas[i] = new Arista(0, 0, 0);
        }
    }

    public String bellmanFord(int verticeInicio) {
        int[] distancias = new int[vertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[verticeInicio] = 0;

        // Relajación de las aristas
        for (int i = 1; i < vertices; i++) {
            for (Arista arista : listaAristas) {
                int u = arista.origen;
                int v = arista.destino;
                int peso = arista.peso;

                if (distancias[u] != Integer.MAX_VALUE && distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                }
            }
        }

        // Verificar ciclos negativos
        for (Arista arista : listaAristas) {
            int u = arista.origen;
            int v = arista.destino;
            int peso = arista.peso;

            if (distancias[u] != Integer.MAX_VALUE && distancias[u] + peso < distancias[v]) {
                return "El grafo contiene un ciclo de peso negativo.";
            }
        }

        return imprimirSolucion(distancias);
    }

    private String imprimirSolucion(int[] distancias) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Vértice \t Distancia desde el origen\n");
        for (int i = 0; i < distancias.length; i++) {
            resultado.append(i).append(" \t\t ")
                    .append(distancias[i] == Integer.MAX_VALUE ? "Infinito" : distancias[i])
                    .append("\n");
        }
        return resultado.toString();
    }
}