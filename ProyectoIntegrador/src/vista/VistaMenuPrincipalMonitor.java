package vista;

import javax.swing.*;

import control.ControladorDatosPersonales;
import control.ControladorNuevaActividad;

import java.awt.*;

public class VistaMenuPrincipalMonitor extends JFrame {
	
	public VistaMenuPrincipalMonitor(String titulo) {
		inicializarComponentes();
	}
	public void inicializarComponentes() {
		setSize(800, 539);
	    //indicar que se hace cuando se cierra la ventana
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //centramos la ventana 
	    setLocationRelativeTo(null);
	    //establecemos el diseño del layout
	    getContentPane().setLayout(null);
	    JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 22);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Actividades");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ver todas las actividades");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nueva Actividad");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ControladorNuevaActividad(this));
		
		
		//JSeparator separator = new JSeparator();
		//mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mis actividades");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Salas");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Datos Personales");
		menuBar.add(mnNewMenu_2);
		JMenuItem menuItemDatos = new JMenuItem("Ver Datos");
		mnNewMenu_2.add(menuItemDatos);
		
		menuItemDatos.addActionListener(new ControladorDatosPersonales(this));
		
		
		JScrollPane scrollPaneles = new JScrollPane();
		scrollPaneles.setBounds(0, 33, 786, 458);
		getContentPane().add(scrollPaneles);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 234, 230));
		scrollPaneles.setViewportView(panel);

		
		ImageIcon icono = new ImageIcon(getClass().getResource("/EuroSportsClubsinfondoR.png"));
        JLabel lblIcono = new JLabel(icono);
        panel.add(lblIcono);
        
		
	}
	public void hacerVisible() {
    	setVisible(true);
    }
	
	
}
