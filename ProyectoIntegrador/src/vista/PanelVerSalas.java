package vista;

import modelo.Sala;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel que muestra un lista con información sobre las salas.
 *
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class PanelVerSalas extends JPanel {

    private JList<String> listaSalas;
    private DefaultListModel<String> modeloLista;

    public PanelVerSalas(List<Sala> salas) {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 210, 210));

        JLabel titulo = new JLabel("Salas disponibles", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(titulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        for (Sala s : salas) {
            String texto = s.getTipoSala() + " - Capacidad: " + s.getCapacidad();
            modeloLista.addElement(texto);
        }

        listaSalas = new JList<>(modeloLista);
        listaSalas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        listaSalas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaSalas);
        add(scroll, BorderLayout.CENTER);
    }
}