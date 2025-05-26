package control;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import modelo.Usuario;
import vista.PanelMisActividadesUsuario;
import vista.VistaMenuPrincipalUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Controlador que se encarga de mostrar al usuario las actividades en las que está inscrito.
 * Recupera las actividades desde la base de datos y las presenta en el panel correspondiente.
 *
 * Permite también al usuario darse de baja de las actividades desde el mismo panel.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorMisActividadesUsuario implements ActionListener {
	
	//Atributos
	private VistaMenuPrincipalUsuario vista;
	private Usuario usuario;
	
	//Constructor
	public ControladorMisActividadesUsuario(VistaMenuPrincipalUsuario vista, Usuario usuario) {
		this.vista=vista;
		this.usuario=usuario;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 AccesoBBDDLogin acceso = new AccesoBBDDLogin();
	        Connection conn = acceso.getConexion();

	        if (conn != null) {
	            List<Actividad> actividades = acceso.obtenerActividadesInscritasDeUsuario(conn, usuario.getIdUsuario());
	            acceso.terminarConexion(conn);

	            PanelMisActividadesUsuario panel = new PanelMisActividadesUsuario(actividades);
	            new ControladorBorrarInscripcion(panel, usuario);
	            vista.cambiarPanel(panel);
	        } else {
	            System.out.println("No se pudo conectar a la base de datos.");
	        }
		
	}
	


}
