package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.VistaMenuPrincipalMonitor;

public class ControladorDatosPersonales implements ActionListener {
	private VistaMenuPrincipalMonitor vista;

    public ControladorDatosPersonales(VistaMenuPrincipalMonitor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(vista, "Has pinchado en 'Ver Datos Personales'");
    }
}


