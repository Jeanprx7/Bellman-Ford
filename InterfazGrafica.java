import javax.swing.*;

public class InterfazGrafica extends JFrame {

    private Grafo grafo;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JTextField txtPeso;
    private JButton agregarAristaButton;
    private JButton calcularBellmanFordButton;
    private JPanel pGeneral;
    private JTextArea txtResultados;

    public InterfazGrafica() {
        // Inicializar el grafo con un número fijo de vértices y aristas
        int vertices = 5;  // Cambiar según sea necesario
        int aristas = 8;   // Cambiar según sea necesario
        grafo = new Grafo(vertices, aristas);


        //  botón "Agregar Arista"
        agregarAristaButton.addActionListener(e -> {
            try {
                // Leer datos de los campos de texto
                int origen = Integer.parseInt(txtOrigen.getText());
                int destino = Integer.parseInt(txtDestino.getText());
                int peso = Integer.parseInt(txtPeso.getText());

                // Agregar la arista al grafo
                for (int i = 0; i < grafo.listaAristas.length; i++) {
                    if (grafo.listaAristas[i].peso == 0) { // Buscar espacio vacío
                        grafo.listaAristas[i] = new Arista(origen, destino, peso);
                        txtResultados.append("Arista agregada: " + origen + " -> " + destino + " (Peso: " + peso + ")\n");
                        break;
                    }
                }

                // Limpiar campos de texto
                txtOrigen.setText("");
                txtDestino.setText("");
                txtPeso.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce valores numéricos válidos.");
            }
        });

        // Lógica del botón "Calcular Bellman-Ford"
        calcularBellmanFordButton.addActionListener(e -> {
            try {
                // Solicitar vértice de inicio al usuario
                int verticeInicio = Integer.parseInt(
                        JOptionPane.showInputDialog(this, "Introduce el vértice de inicio:"));

                // Calcular distancias usando el algoritmo Bellman-Ford
                String resultado = grafo.bellmanFord(verticeInicio);
                txtResultados.append("\nResultados Bellman-Ford desde el vértice " + verticeInicio + ":\n" + resultado);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Introduce un vértice de inicio válido.");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InterfazGrafica");
        frame.setContentPane(new InterfazGrafica().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}