package vista;

import java.awt.*;

import javax.swing.*;

public class VistaMenuPrincipalMonitor extends JFrame {
	JLabel lblSeleccion;
	JButton botonMonitor;
	JButton botonUsuario;
	public VistaMenuPrincipalMonitor() {
		inicializarComponentes();
	}
	public void inicializarComponentes() {
		setSize(542, 526);
	    //indicar que se hace cuando se cierra la ventana
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //centramos la ventana 
	    setLocationRelativeTo(null);
	    //establecemos el diseño del layout
	    getContentPane().setLayout(null);
	    JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 518, 22);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Actividades");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ver todas las actividades");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nueva Actividad");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		//JSeparator separator = new JSeparator();
		//mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mis actividades");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Salas");
		menuBar.add(mnNewMenu_1);
		JMenu mnNewMenu_2 = new JMenu("Datos Personales");
		menuBar.add(mnNewMenu_2);
		
	}
	public void hacerVisible() {
    	setVisible(true);
    }
}
