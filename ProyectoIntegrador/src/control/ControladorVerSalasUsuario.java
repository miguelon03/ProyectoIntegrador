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
