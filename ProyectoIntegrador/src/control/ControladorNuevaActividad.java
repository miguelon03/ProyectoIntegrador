package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;

import bbdd.AccesoBBDDLogin;
import modelo.Usuario;
import vista.PanelNuevaActividad;
import vista.VistaMenuPrincipalMonitor;

/**
 * Controlador que gestiona la creación de nuevas actividades por parte del monitor.
 * Carga las salas disponibles desde la base de datos y presenta el panel de creación.
 * También se encarga de asociar el botón "Añadir" al proceso de validación e inserción
 * de la nueva actividad en la base de datos.
 *
 * Evita solapamientos de actividades del mismo monitor en el mismo horario.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorNuevaActividad implements ActionListener {

    private VistaMenuPrincipalMonitor vista;

    public ControladorNuevaActividad(VistaMenuPrincipalMonitor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AccesoBBDDLogin acceso = new AccesoBBDDLogin();
        Connection conn = acceso.getConexion();

        if (conn != null) {
            Usuario monitor = vista.getUsuario(); // El monitor que inició sesión

            

            PanelNuevaActividad panel = new PanelNuevaActividad(monitor);
            vista.cambiarPanel(panel);

            new ControladorBotonAnadir(panel, monitor); // Controlador que gestiona validaciones y registro

        } else {
            JOptionPane.showMessageDialog(vista, "Error de conexión con la base de datos.");
        }
    }
}

