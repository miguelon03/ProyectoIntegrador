package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.PanelDatosPersonales;
import vista.VistaMenuPrincipalUsuario;

/**
 * Controlador que permite mostrar los datos personales del usuario en su panel correspondiente.
 * Recupera la información del usuario que ha iniciado sesión y la muestra en el panel de datos personales.
 *
 * Se utiliza dentro del menú principal del usuario para acceder a sus datos personales.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorDatosPersonalesUsuario implements ActionListener {
	private VistaMenuPrincipalUsuario vistaUsuario;
	private PanelDatosPersonales panel;
	
	public ControladorDatosPersonalesUsuario(VistaMenuPrincipalUsuario vistaUsuario){
		this.vistaUsuario=vistaUsuario;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Usuario usuario = vistaUsuario.getUsuario();
		panel = new PanelDatosPersonales(usuario);
		new ControladorCambiarContrasena(panel, usuario);
		panel.mostrarDatosUsuario(usuario);//mostramos los datos del usuario
    	vistaUsuario.cambiarPanel(panel);
		
	}

}
