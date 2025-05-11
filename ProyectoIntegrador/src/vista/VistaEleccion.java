package vista;

import java.awt.*;
import javax.swing.*;

public class VistaEleccion extends JFrame {
	JButton botonMonitor;
	JButton botonUsuario;
	JLabel lblAcceso;
	public VistaEleccion () {
		getContentPane().setBackground(new Color(254, 235, 235));
    	inicializarComponentes();
    }
    public void inicializarComponentes() {
    	 
        //Ponemos el tamaño
    	setSize(518, 588);
        //indicar que se hace cuando se cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centramos la ventana 
        setLocationRelativeTo(null);
        //establecemos el diseño del layout
        getContentPane().setLayout(null);
        // Creamos el boton
        botonMonitor = new JButton("Monitor");
        botonMonitor.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonMonitor.setBackground(new Color(255, 0, 0));
        //lo colocamos en la ventana
        botonMonitor.setBounds(83, 379, 140, 63);
        //añadimos el botón de monitor
        getContentPane().add(botonMonitor);
        
        //creamos el boton de usuario
        botonUsuario = new JButton("Usuario");
        botonUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonUsuario.setBackground(new Color(255, 0, 0));
        //le damos tamaño
        botonUsuario.setBounds(278, 379, 140, 63);
        //añadimos el boton de usuario
        getContentPane().add(botonUsuario);
        
        lblAcceso = new JLabel("¿Accedes como usuario o monitor?");
        //establecemos el font
        lblAcceso.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        lblAcceso.setBounds(97, 290, 321, 52);
        //añadimos el label de usuario
        getContentPane().add(lblAcceso);
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/EuroSportsClubsinfondoR.png"));
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setBounds(130, 11, 245, 245);
        getContentPane().add(lblIcono);
    }
}
