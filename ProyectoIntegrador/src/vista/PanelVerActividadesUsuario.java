package vista;

import modelo.Actividad;

import javax.swing.*;

import control.ControladorInscripcion;

import java.awt.*;
import java.util.List;

/**
 * Panel que muestra al usuario una lista de todas las actividades disponibles 
 * para inscribirse. Permite seleccionar una actividad y realizar la inscripción 
 * si hay plazas disponibles.
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class PanelVerActividadesUsuario extends JPanel {
	
	//Atributos
	private DefaultListModel<String> modeloLista;
    private JList<String> listaActividades;
    private JButton btnInscribirse;
    private List<Actividad> actividades;
    
    
    //Constructor
    public PanelVerActividadesUsuario(List<Actividad> actividades) {
        this.actividades = actividades;
        inicializarComponentes();
    }
    
   //Método para inicializar el panel
    public void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 210, 210));

        JLabel titulo = new JLabel("Actividades disponibles", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(titulo, BorderLayout.NORTH);

       //Lista con las actividades para inscribirse 
        modeloLista = new DefaultListModel<>();
        for (Actividad act : actividades) {
            String item = act.getNombreActividad()
                    + " - " + act.getFecha()
                    + " " + act.getHora()
                    + " - " + act.getSala().getTipoSala()
                    + " - " + act.getMonitor();
            modeloLista.addElement(item);
        }

        listaActividades = new JList<>(modeloLista);
        listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaActividades);
        add(scrollPane, BorderLayout.CENTER);

        //Botón de inscribirse
        JPanel panelBoton = new JPanel();
        btnInscribirse = new JButton("Inscribirse");
        btnInscribirse.setBackground(new Color(0, 153, 51));
        btnInscribirse.setForeground(Color.WHITE);
        btnInscribirse.setFont(new Font("Arial", Font.PLAIN, 14));
        panelBoton.add(btnInscribirse);
        add(panelBoton, BorderLayout.SOUTH);
    }
    
   //Getters para usar el controlador
    public JButton getBtnInscribirse() {
        return btnInscribirse;
    }

    public JList<String> getListaActividades() {
        return listaActividades;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

}
