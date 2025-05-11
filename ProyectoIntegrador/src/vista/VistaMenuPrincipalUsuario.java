package vista;

import java.awt.*;
import javax.swing.*;


public class VistaMenuPrincipalUsuario extends JFrame {
	JLabel titulo;
	public VistaMenuPrincipalUsuario() {
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
		
		
		//JSeparator separator = new JSeparator();
		//mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Mis actividades");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Salas");
		menuBar.add(mnNewMenu_1);
		JMenu mnNewMenu_2 = new JMenu("Datos Personales");
		menuBar.add(mnNewMenu_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 786, 458);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 234, 230));
		scrollPane.setViewportView(panel);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/EuroSportsClubsinfondoR.png"));
        JLabel lblIcono = new JLabel(icono);
        panel.add(lblIcono);
        
		
	}
	public void hacerVisible() {
    	setVisible(true);
    }

}
