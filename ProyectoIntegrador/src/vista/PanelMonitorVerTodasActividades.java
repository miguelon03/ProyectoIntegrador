package vista;

import java.awt.*;
import javax.swing.*;

import control.ControladorBorrar;
import control.ControladorEditar;

public class PanelMonitorVerTodasActividades extends JPanel {
	
	 private DefaultListModel<String> modeloLista;
	    private JList<String> listaActividades;
	    private JButton btnEditar, btnBorrar;

	    public PanelMonitorVerTodasActividades() {
	    	inicializarComponentes();
	    }
	    
	    public void inicializarComponentes() {
	    	
	        setLayout(new BorderLayout());
	        setBackground(new Color(230, 210, 210));

	        JLabel titulo = new JLabel("Lista de actividades", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 16));
	        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        add(titulo, BorderLayout.NORTH);

	        modeloLista = new DefaultListModel<>();
	        modeloLista.addElement("Pilates - 11 abril 2024 - 16:00 - Sala Multiusos");
	        modeloLista.addElement("Pilates - 12 abril 2024 - 16:00 - Sala Multiusos");
	        modeloLista.addElement("Fuerza - 14 abril 2024 - 10:00 - Gimnasio");
	        modeloLista.addElement("Natación - 14 abril 2024 - 12:30 - Piscina");
	        modeloLista.addElement("Fútbol - 15 abril 2024 - 12:00 - Campo 1");

	        listaActividades = new JList<>(modeloLista);
	        listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        listaActividades.setSelectedIndex(2);
	        JScrollPane scrollPane = new JScrollPane(listaActividades);
	        add(scrollPane, BorderLayout.CENTER);

	        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	        btnEditar = new JButton("Editar");
	        btnBorrar = new JButton("Borrar");

	        JButton[] botones = {btnEditar, btnBorrar};
	        for (JButton boton : botones) {
	            boton.setBackground(new Color(204, 0, 0));
	            boton.setForeground(Color.WHITE);
	            boton.setFocusPainted(false);
	            boton.setFont(new Font("Arial", Font.PLAIN, 14));
	            boton.setPreferredSize(new Dimension(130, 30));
	        }

	        panelBotones.add(btnEditar);
	        panelBotones.add(btnBorrar);
	        add(panelBotones, BorderLayout.SOUTH);
	        
	        new ControladorEditar(btnEditar);
	        new ControladorBorrar(btnBorrar);
	    }

	    public JList<String> getListaActividades() {
	        return listaActividades;
	    }

	    public DefaultListModel<String> getModeloLista() {
	        return modeloLista;
	    }

	    public JButton getBtnEditar() {
	        return btnEditar;
	    }

	    public JButton getBtnBorrar() {
	        return btnBorrar;
	    }
	
}


