package vista;

import java.awt.*;
import javax.swing.*;

public class VistaEleccion extends JFrame {
	JButton botonMonitor;
	JButton botonUsuario;
	public VistaEleccion () {
    	inicializarComponentes();
    }
    public void inicializarComponentes() {
    	 
        //Ponemos el tamaño
        setSize(500, 300);
        //indicar que se hace cuando se cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centramos la ventana 
        setLocationRelativeTo(null);
        //establecemos el diseño del layout
        getContentPane().setLayout(null);
        // Creamos el boton
        botonMonitor = new JButton("Monitor");
        //lo colocamos en la ventana
        botonMonitor.setBounds(96, 144, 140, 63);
        //añadimos el botón de monitor
        getContentPane().add(botonMonitor);
        
        //creamos el boton de usuario
        botonUsuario = new JButton("Usuario");
        //le damos tamaño
        botonUsuario.setBounds(284, 144, 140, 63);
        //añadimos el boton de usuario
        getContentPane().add(botonUsuario);
        
        JTextArea textArea = new JTextArea("¿Entras como usuario o como monitor?");
        textArea.setBounds(111, 41, 293, 47);
        getContentPane().add(textArea);
    }
}
