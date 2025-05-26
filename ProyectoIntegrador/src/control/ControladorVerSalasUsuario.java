package control;

import bbdd.AccesoBBDDLogin;
import modelo.Sala;
import vista.PanelVerSalas;
import vista.VistaMenuPrincipalUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Controlador encargado de mostrar al usuario la lista de salas disponibles en el sistema.
 * Se conecta a la base de datos para obtener los datos de todas las salas y actualiza
 * el panel principal con una vista que las presenta al usuario.
 * 
 * Este controlador proporciona una visión general de los espacios disponibles para inscribirse en actividades.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorVerSalasUsuario implements ActionListener {

    private VistaMenuPrincipalUsuario vista;

    public ControladorVerSalasUsuario(VistaMenuPrincipalUsuario vista) {
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
