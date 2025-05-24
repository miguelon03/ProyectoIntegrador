package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.PanelDatosPersonales;
import vista.VistaMenuPrincipalUsuario;

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
