import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bellman_Ford_GUI {
    private Grafo grafo;
    private JButton agregarAristaButton;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JTextField txtPeso;
    private JTextArea txtResultados;
    private JButton calcularBellmanFordButton;
    private JPanel pGeneral;

    public Bellman_Ford_GUI() {
        // Inicializar el grafo con un número fijo de vértices y aristas
        int vertices = 5;  // Cambiar según sea necesario
        int aristas = 8;   // Cambiar según sea necesario
        grafo = new Grafo(vertices, aristas);

        agregarAristaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    txtOrigen.setText(" ");
                    txtDestino.setText(" ");
                    txtPeso.setText(" ");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce valores numéricos válidos.");
                }
            }
        });
        calcularBellmanFordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Solicitar vértice de inicio al usuario
                    int verticeInicio = Integer.parseInt(
                            JOptionPane.showInputDialog(this, "Introduce el vértice de inicio:"));

                    // Calcular distancias usando el algoritmo Bellman-Ford
                    String resultado = grafo.bellmanFord(verticeInicio);
                    txtResultados.append("\nResultados Bellman-Ford desde el vértice " + verticeInicio + ":\n" + resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce un vértice de inicio válido.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("prueba");
        frame.setContentPane(new Bellman_Ford_GUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
