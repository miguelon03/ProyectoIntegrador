package vista;

import javax.swing.*;

import control.ControladorDatosPersonalesMonitor;
import control.ControladorMisActividadesMonitor;
import control.ControladorNuevaActividad;
import control.ControladorVerActividadesMonitor;
import control.ControladorVerSalasMonitor;
import modelo.Usuario;

import java.awt.*;

public class VistaMenuPrincipalMonitor extends JFrame {
	JScrollPane scrollPaneles;
	private Usuario usuario;
	
	public VistaMenuPrincipalMonitor(Usuario usuario) {
		this.usuario=usuario;
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
		mntmNewMenuItem.addActionListener(new ControladorVerActividadesMonitor(this));
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nueva Actividad");
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ControladorNuevaActividad(this));
		
		
		
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Mis actividades");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ControladorMisActividadesMonitor(this, String.valueOf(usuario.getIdUsuario())));

		
		JMenu mnNewMenu_1 = new JMenu("Salas");
		menuBar.add(mnNewMenu_1);
		JMenuItem itemVerSalas = new JMenuItem("Ver Salas");
		mnNewMenu_1.add(itemVerSalas);
		itemVerSalas.addActionListener(new ControladorVerSalasMonitor(this));
		
		JMenu mnNewMenu_2 = new JMenu("Datos Personales");
		menuBar.add(mnNewMenu_2);
		JMenuItem menuItemDatos = new JMenuItem("Ver Datos");
		mnNewMenu_2.add(menuItemDatos);
		
		menuItemDatos.addActionListener(new ControladorDatosPersonalesMonitor(this));
		
		
		scrollPaneles = new JScrollPane();
		scrollPaneles.setBounds(0, 33, 786, 458);
		getContentPane().add(scrollPaneles);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 234, 230));
		scrollPaneles.setViewportView(panel);

		
		ImageIcon icono = new ImageIcon(getClass().getResource("/EuroSportsClubsinfondoR.png"));
        JLabel lblIcono = new JLabel(icono);
        panel.add(lblIcono);
        
        JLabel lblBienvenida = new JLabel("¡Bienvenido/a " + usuario.getNombre() +" "+ usuario.getApellidos()+ "!", SwingConstants.CENTER);
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
