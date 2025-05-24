package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import modelo.Usuario;
import vista.PanelVerActividadesUsuario;
import vista.VistaMenuPrincipalUsuario;

public class ControladorVerActividadesUsuario implements ActionListener {

    private VistaMenuPrincipalUsuario vista;
    private Usuario usuario;

    public ControladorVerActividadesUsuario(VistaMenuPrincipalUsuario vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AccesoBBDDLogin acceso = new AccesoBBDDLogin();
        Connection conn = acceso.getConexion();

        if (conn != null) {
            List<Actividad> actividades = acceso.obtenerTodasLasActividades(conn);
            acceso.terminarConexion(conn);

            PanelVerActividadesUsuario panel = new PanelVerActividadesUsuario(actividades);
            new ControladorInscripcion(panel, usuario);
            vista.cambiarPanel(panel);
        } else {
            System.out.println("Error al conectar con la base de datos.");
        }
    }
}

