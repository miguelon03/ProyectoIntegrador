package vista;

import java.awt.*;
import javax.swing.*;

import control.ControladorDatosPersonalesMonitor;
import control.ControladorDatosPersonalesUsuario;
import control.ControladorMisActividadesUsuario;
import control.ControladorVerActividadesUsuario;
import control.ControladorVerSalasMonitor;
import control.ControladorVerSalasUsuario;
import modelo.Usuario;


public class VistaMenuPrincipalUsuario extends JFrame {
	JLabel titulo;
	JScrollPane scrollPaneles;
	private Usuario usuario;
	
	public VistaMenuPrincipalUsuario(Usuario usuario) {
	    this.usuario = usuario;
	    inicializarComponentes();
	}
	public void inicializarComponentes() {
		setSize(800, 539);
	    //indicar que se hace cuando se cierra la ventana
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //centramos la ventana 
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(null);//sin layout 
	    
	   
	    
	    JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 22);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Actividades");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ver todas las actividades");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ControladorVerActividadesUsuario(this, usuario));
		
		
		//JSeparator separator = new JSeparator();
		//mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Mis actividades");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ControladorMisActividadesUsuario(this, usuario));
		
		
		JMenu mnNewMenu_1 = new JMenu("Salas");
		menuBar.add(mnNewMenu_1);
		JMenuItem itemVerSalas = new JMenuItem("Ver Salas");
		mnNewMenu_1.add(itemVerSalas);
		itemVerSalas.addActionListener(new ControladorVerSalasUsuario(this));
		
		JMenu mnNewMenu_2 = new JMenu("Datos Personales");
		menuBar.add(mnNewMenu_2);
		JMenuItem menuItemDatos = new JMenuItem("Ver Datos");
		mnNewMenu_2.add(menuItemDatos);
		menuItemDatos.addActionListener(new ControladorDatosPersonalesUsuario(this));
		
		scrollPaneles = new JScrollPane();
		scrollPaneles.setBounds(0, 33, 786, 458);
		getContentPane().add(scrollPaneles);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 234, 230));
		scrollPaneles.setViewportView(panel);
		
		ImageIcon icono = new ImageIcon(getClass().getResource("/EuroSportsClubsinfondoR.png"));
        JLabel lblIcono = new JLabel(icono);
        panel.add(lblIcono);
        
        JLabel lblBienvenida = new JLabel("¡Bienvenido/a " + usuario.getNombre()+" "+ usuario.getApellidos() + "!", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        lblBienvenida.setForeground(new Color(102, 0, 0));
        panel.add(lblBienvenida);
        
		
	}
	public void hacerVisible() {
    	setVisible(true);
    }
	
	public void cambiarPanel(JPanel panel) {
		scrollPaneles.setViewportView(panel);
	}
	
	public Usuario getUsuario() {
	    return usuario;
	}

}
