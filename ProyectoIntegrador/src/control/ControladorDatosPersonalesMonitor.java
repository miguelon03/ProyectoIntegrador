package control;
import modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.PanelDatosPersonales;
import vista.VistaMenuPrincipalMonitor;

/**
 * Controlador que permite mostrar los datos personales del monitor en su panel correspondiente.
 * Recupera la información del monitor que ha iniciado sesión y la muestra en el panel de datos personales.
 *
 * Se utiliza dentro del menú principal del monitor para acceder a sus datos personales.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorDatosPersonalesMonitor implements ActionListener {
	private VistaMenuPrincipalMonitor vista;
	
	private PanelDatosPersonales panel;

    public ControladorDatosPersonalesMonitor(VistaMenuPrincipalMonitor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	Usuario usuario =vista.getUsuario();
    	
    	panel = new PanelDatosPersonales(usuario);
    	new ControladorCambiarContrasena(panel, usuario);
    	panel.mostrarDatosUsuario(usuario);
    	vista.cambiarPanel(panel);
        
    }
}


