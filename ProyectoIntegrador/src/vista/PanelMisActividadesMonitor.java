package vista;


import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.util.ArrayList;

import control.ControladorBorrar;
import control.ControladorEditar;
import control.ControladorEditarActividadMonitor;
import control.ControladorGuardarCambiosActividad;
import modelo.Actividad;


public class PanelMisActividadesMonitor extends JPanel {
	
	    private List<Actividad> actividades;
	    private DefaultListModel<String> modeloLista;
	    private JList<String> listaActividades;
	    private JButton btnEditar, btnBorrar;

	    public PanelMisActividadesMonitor(List<Actividad> actividades) {
	        this.actividades = actividades;
	        inicializarComponentes(); // Se llama después de asignar actividades
	    }

	    public void inicializarComponentes() {
	        setLayout(new BorderLayout());
	        setBackground(new Color(230, 210, 210));

	        JLabel titulo = new JLabel("Lista de actividades", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 16));
	        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        add(titulo, BorderLayout.NORTH);

	        modeloLista = new DefaultListModel<>();

	        // Aquí usamos las actividades reales
	        for (Actividad act : actividades) {
	            String item = act.getNombreActividad()
	                + " - " + act.getSala().getTipoSala()
	                + " - " + act.getFecha()
	                + " - " + act.getHora()
	                + " - " + act.getMonitor();
	            modeloLista.addElement(item);
	        }

	        listaActividades = new JList<>(modeloLista);
	        listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        if (!modeloLista.isEmpty()) {
	            listaActividades.setSelectedIndex(0);
	        }

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

	        // Asociar controladores con la lista real
	        //Bototn editar para abrir el panel de editar actividad
	        btnEditar.addActionListener(e -> {
	            int seleccion = listaActividades.getSelectedIndex();
	            if (seleccion != -1) {
	                Actividad actividadSeleccionada = actividades.get(seleccion);
	                PanelEditarActividad panelEditar = new PanelEditarActividad(actividadSeleccionada);
	                new ControladorGuardarCambiosActividad(panelEditar, (VistaMenuPrincipalMonitor) SwingUtilities.getWindowAncestor(this));
	                ((VistaMenuPrincipalMonitor) SwingUtilities.getWindowAncestor(this)).cambiarPanel(panelEditar);
	            } else {
	                JOptionPane.showMessageDialog(this, "Selecciona una actividad para editar.");
	            }
	        });
	        
	        
	        new ControladorBorrar(btnBorrar, listaActividades, actividades, modeloLista);
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



