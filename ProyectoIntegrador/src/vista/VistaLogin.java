package vista;
import javax.swing.*;

import control.ControladorLogin;

import java.awt.*;

public class VistaLogin extends JFrame {
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JTextField txtUsuario;
	private JButton btnInicioSesion;
	private JPasswordField password;
	private ControladorLogin controlador;
    public VistaLogin(String titulo) {
    	//ponemos el titulo
    	super(titulo);
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
        // Creamos el label y y el cudro de texto para poner el usuario
        lblUsuario = new JLabel("ID Usuario");
        //establecemos el font
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        //lo colocamos en la ventana
        lblUsuario.setBounds(65, 267, 114, 52);
        //añadimos el label de usuario
        getContentPane().add(lblUsuario);
        
        //creamos el textfield del usuario
        txtUsuario = new JTextField("");
        //establecemos el font
        txtUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        txtUsuario.setBounds(205,267 ,170 ,53 );
        //añadimos el label de usuario
        getContentPane().add(txtUsuario);
        
        //creamos el label de la contraseña
        lblPassword = new JLabel("Contraseña");
        //establecemos el font
        lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
        //lo colocamos en la ventana
        lblPassword.setBounds(65,335,132,100);
        //añadimos el label de usuario
        getContentPane().add(lblPassword);
        
        //creamos el passwordfield
        password= new JPasswordField("");
        //establecemos el font
        password.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        password.setBounds(205, 359, 170, 53);
        //añadimos el label de usuario
        getContentPane().add(password);
        
        
        //creamos el boton de incicio de sesion
        btnInicioSesion = new JButton("Iniciar sesión");
        btnInicioSesion.setBounds(143, 459, 200, 52);
        btnInicioSesion.setFocusPainted(false);
        //establecemos el font
        btnInicioSesion.setFont(new Font("Arial", Font.BOLD, 18));
        getContentPane().add(btnInicioSesion);
        
        controlador = new ControladorLogin(this);
		btnInicioSesion.addActionListener(controlador);
        
        
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/EuroSportsClubsinfondoR.png"));
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setBounds(130, 11, 245, 245);
        getContentPane().add(lblIcono);
        
        
        
        
    }
    public void hacerVisible() {
    	setVisible(true);
    }
    public JButton getIncioSesion() {
		return btnInicioSesion;
	}
    
    public String getUsuario() {
		return txtUsuario.getText();
	}

	public JPasswordField getPassword() {
		return password;
	}
}
