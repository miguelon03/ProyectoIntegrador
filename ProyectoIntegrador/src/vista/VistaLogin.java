package vista;
import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
	JLabel lblUsuario;
	JLabel lblPassword;
	JTextField txtUsuario;
	JButton inicioSesion;
	JPasswordField password;
    public VistaLogin(String titulo) {
    	//ponemos el titulo
    	super(titulo);
    	inicializarComponentes();
    }
    public void inicializarComponentes() {
    	 
        //Ponemos el tamaño
        setSize(518, 397);
        //indicar que se hace cuando se cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //centramos la ventana 
        setLocationRelativeTo(null);
        //establecemos el diseño del layout
        getContentPane().setLayout(null);
        // Creamos el label y y el cudro de texto para poner el usuario
        lblUsuario = new JLabel("Usuario");
        //establecemos el font
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        lblUsuario.setBounds(65, 53, 78, 52);
        //añadimos el label de usuario
        getContentPane().add(lblUsuario);
        
        //creamos el textfield del usuario
        txtUsuario = new JTextField("");
        //establecemos el font
        txtUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        txtUsuario.setBounds(205,53 ,170 ,53 );
        //añadimos el label de usuario
        getContentPane().add(txtUsuario);
        
        //creamos el label de la contraseña
        lblPassword = new JLabel("Contraseña");
        //establecemos el font
        lblPassword.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        lblPassword.setBounds(65,103,132,100);
        //añadimos el label de usuario
        getContentPane().add(lblPassword);
        
        //creamos el passwordfield
        password= new JPasswordField("");
        //establecemos el font
        password.setFont(new Font("Arial", Font.BOLD, 18));
        //lo colocamos en la ventana
        password.setBounds(207, 121, 168, 64);
        //añadimos el label de usuario
        getContentPane().add(password);
        
        
        //creamos el boton de incicio de sesion
        inicioSesion = new JButton("Iniciar Sesion");
        inicioSesion.setBounds(119, 261, 200, 52);
        //establecemos el font
        inicioSesion.setFont(new Font("Arial", Font.BOLD, 18));
        getContentPane().add(inicioSesion);
        
    }
    public void hacerVisible() {
    	setVisible(true);
    }
    public JButton getIncioSesion() {
		return inicioSesion;
	}
	
}
