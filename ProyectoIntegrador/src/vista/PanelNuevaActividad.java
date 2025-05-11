package vista;

import javax.swing.*;
import java.awt.*;

public class PanelNuevaActividad extends JPanel {
	 private JTextField campoActividad;
	    private JTextField campoFecha;
	    private JComboBox<String> comboHora;
	    private JComboBox<String> comboLugar;
	    private JButton btnAnadir;

	    public PanelNuevaActividad() {
	    	inicializarComponentes();
	    }
	    
	    public void inicializarComponentes() {
	    	
	        setLayout(new BorderLayout());
	        setBackground(new Color(230, 210, 210));

	        JLabel titulo = new JLabel("Añade una nueva actividad", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 16));
	        titulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        add(titulo, BorderLayout.NORTH);

	        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
	        formPanel.setBackground(new Color(230, 210, 210));
	        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

	        campoActividad = new JTextField();
	        campoFecha = new JTextField();

	        comboHora = new JComboBox<>();
	        for (int h = 10; h <= 20; h++) {
	            comboHora.addItem(String.format("%02d:00", h));
	        }

	        comboLugar = new JComboBox<>();
	        comboLugar.addItem("Sala Multiusos");
	        comboLugar.addItem("Gimnasio");
	        comboLugar.addItem("Piscina");
	        comboLugar.addItem("Campo 1");


	        formPanel.add(new JLabel("Actividad:"));
	        formPanel.add(campoActividad);

	        formPanel.add(new JLabel("Fecha:"));
	        formPanel.add(campoFecha);

	        formPanel.add(new JLabel("Hora:"));
	        formPanel.add(comboHora);

	        formPanel.add(new JLabel("Lugar/Sala:"));
	        formPanel.add(comboLugar);

	        btnAnadir = new JButton("Añadir");
	        btnAnadir.setBackground(new Color(204, 0, 0));
	        btnAnadir.setForeground(Color.WHITE);
	        btnAnadir.setFont(new Font("Arial", Font.BOLD, 14));
	       
	      

	        JPanel botonPanel = new JPanel();
	        botonPanel.setBackground(new Color(230, 210, 210));
	        botonPanel.add(btnAnadir);

	        add(formPanel, BorderLayout.CENTER);
	        add(botonPanel, BorderLayout.SOUTH);
	    }

	    // Getters públicos
	    public String getActividad() {
	    	return campoActividad.getText(); 
	    	}
	    public String getFecha() {
	    	return campoFecha.getText(); 
	    	}
	    public String getHora() {
	    	return (String) comboHora.getSelectedItem();
	    	}
	    public String getLugar() { 
	    	return (String) comboLugar.getSelectedItem(); 
	    	}
	    public JButton getBtnAnadir() { 
	    	return btnAnadir;
	    	}
}
