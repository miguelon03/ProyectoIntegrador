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

/**
 * Controlador que permite a los usuarios visualizar todas las actividades disponibles en el sistema.
 * Recupera la información desde la base de datos y actualiza el panel principal del usuario
 * con una lista de actividades a las que puede inscribirse.
 * 
 * Esta clase facilita la interacción del usuario con las actividades planificadas por los monitores.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
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

