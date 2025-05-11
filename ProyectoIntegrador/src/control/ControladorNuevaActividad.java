package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.PanelNuevaActividad;
import vista.VistaMenuPrincipalMonitor;

public class ControladorNuevaActividad implements ActionListener {
	private VistaMenuPrincipalMonitor vista;

    public ControladorNuevaActividad(VistaMenuPrincipalMonitor vista) {
        this.vista = vista;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(vista, "Has pinchado en 'Nueva actividad'");
	}

    
}
