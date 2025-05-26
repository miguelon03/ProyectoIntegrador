package control;

import bbdd.AccesoBBDDLogin;
import modelo.Sala;
import vista.PanelVerSalas;
import vista.VistaMenuPrincipalMonitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Controlador encargado de mostrar al monitor la lista de salas disponibles en el sistema.
 * Se conecta a la base de datos para obtener los datos de todas las salas y actualiza
 * el panel principal con una vista que las presenta al usuario.
 * 
 * Este controlador proporciona una visión general de los espacios disponibles para programar actividades.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorVerSalasMonitor implements ActionListener {

    private VistaMenuPrincipalMonitor vista;

    public ControladorVerSalasMonitor(VistaMenuPrincipalMonitor vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AccesoBBDDLogin acceso = new AccesoBBDDLogin();
        Connection conn = acceso.getConexion();

        if (conn != null) {
            List<Sala> salas = acceso.obtenerTodasLasSalas(conn);
            acceso.terminarConexion(conn);

            PanelVerSalas panel = new PanelVerSalas(salas);
            vista.cambiarPanel(panel);
        } else {
            JOptionPane.showMessageDialog(vista, "Error de conexión al obtener las salas.");
        }
    }
}
