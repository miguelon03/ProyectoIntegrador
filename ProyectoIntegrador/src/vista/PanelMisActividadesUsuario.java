package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

import modelo.Actividad;

public class PanelMisActividadesUsuario extends JPanel {
	    private DefaultListModel<String> modeloLista;
	    private JList<String> listaActividades;
	    private List<Actividad> actividades;
	    private JButton btnBorrarInscripcion;

	    public PanelMisActividadesUsuario(List<Actividad> actividades) {
	        this.actividades = actividades;
	        inicializarComponentes();
	    }

	    public void inicializarComponentes() {
	    	setLayout(new BorderLayout());
	        setBackground(new Color(230, 210, 210));

	        JLabel titulo = new JLabel("Mis actividades", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 16));
	        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        add(titulo, BorderLayout.NORTH);

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

	        // Panel con el botón
	        JPanel panelBoton = new JPanel();
	        btnBorrarInscripcion = new JButton("Borrar inscripción");
	        btnBorrarInscripcion.setBackground(new Color(204, 0, 0));
	        btnBorrarInscripcion.setForeground(Color.WHITE);
	        btnBorrarInscripcion.setFont(new Font("Arial", Font.PLAIN, 14));
	        panelBoton.add(btnBorrarInscripcion);
	        add(panelBoton, BorderLayout.SOUTH);
	    }

	    
	    //Getters y setters
	    public JList<String> getListaActividades() {
	        return listaActividades;
	    }

	    public List<Actividad> getActividades() {
	        return actividades;
	    }
	    
	    public JButton getBtnBorrarInscripcion() {
	        return btnBorrarInscripcion;
	    }

	    public DefaultListModel<String> getModeloLista() {
	        return modeloLista;
	    }

}
