package control;
import modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.PanelDatosPersonales;
import vista.VistaMenuPrincipalMonitor;

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


