package vista;

import java.awt.*;
import javax.swing.*;

import modelo.Usuario;

/**
 * Ventana que muestra las opciones de acceso de un usuario del ciclo TAFD (monitor o usuario).
 * Dependiendo de la opción que escoja mediante botones accederá al menú principal de monitor o al de usuario.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class VistaEleccion extends JFrame {
	private JButton botonMonitor;
	private JButton botonUsuario;
	JLabel lblAcceso;
	private Usuario usuario;
	public VistaEleccion (Usuario usuario) {
		this.usuario=usuario;
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
        setBotonMonitor(new JButton("Monitor"));
        getBotonMonitor().setFont(new Font("Tahoma", Font.PLAIN, 16));
        botonMonitor.setFocusPainted(false);
        getBotonMonitor().setBackground(new Color(255, 0, 0));
        //lo colocamos en la ventana
        getBotonMonitor().setBounds(83, 379, 140, 63);
        //añadimos el botón de monitor
        getContentPane().add(getBotonMonitor());
        
        //creamos el boton de usuario
        setBotonUsuario(new JButton("Usuario"));
        getBotonUsuario().setFont(new Font("Tahoma", Font.PLAIN, 16));
        getBotonUsuario().setBackground(new Color(255, 0, 0));
        //le damos tamaño
        getBotonUsuario().setBounds(278, 379, 140, 63);
        //añadimos el boton de usuario
        getContentPane().add(getBotonUsuario());
        
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
    
    public void hacerVisible() {
    	setVisible(true);
    }
	public JButton getBotonMonitor() {
		return botonMonitor;
	}
	public void setBotonMonitor(JButton botonMonitor) {
		this.botonMonitor = botonMonitor;
		botonMonitor.setForeground(new Color(255, 255, 255));
	}
	public JButton getBotonUsuario() {
		return botonUsuario;
	}
	public void setBotonUsuario(JButton botonUsuario) {
		this.botonUsuario = botonUsuario;
		botonUsuario.setForeground(new Color(255, 255, 255));
	}
	
	public Usuario getUsuario() {
	    return usuario;
	}
}
