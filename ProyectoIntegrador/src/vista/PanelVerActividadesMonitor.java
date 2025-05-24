package vista;

import modelo.Actividad;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

public class PanelVerActividadesMonitor extends JPanel {
	
	 private DefaultListModel<String> modeloLista;
	    private JList<String> listaActividades;

	    public PanelVerActividadesMonitor(List<Actividad> actividades) {
	        setLayout(new BorderLayout());
	        setBackground(new Color(230, 210, 210));

	        JLabel titulo = new JLabel("Todas las actividades registradas", SwingConstants.CENTER);
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
	    }
	
	

}
