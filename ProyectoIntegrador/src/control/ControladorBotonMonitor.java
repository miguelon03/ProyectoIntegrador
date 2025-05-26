
package control;

/**
 * Controlador que gestiona la acción del botón "Monitor" en la vista de elección.
 * Cuando se selecciona esta opción, se cierra la vista actual y se muestra el menú principal del monitor.
 *
 * Este controlador se utiliza en {@link vista.VistaEleccion} para los usuarios del ciclo TAFD.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Usuario;
import vista.VistaEleccion;
import vista.VistaMenuPrincipalMonitor;

public class ControladorBotonMonitor implements ActionListener {

    private VistaEleccion vistaEleccion;
    private Usuario usuario;

    public ControladorBotonMonitor(VistaEleccion vistaEleccion,Usuario usuario) {
        this.vistaEleccion = vistaEleccion;
        this.usuario = usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Cierra la ventana actual
        vistaEleccion.dispose();;
    	VistaMenuPrincipalMonitor menuMonitor = new VistaMenuPrincipalMonitor(usuario);
        menuMonitor.hacerVisible();   // Muestra el menú principal para monitores
    }
}
