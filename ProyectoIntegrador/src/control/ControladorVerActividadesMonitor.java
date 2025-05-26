package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.List;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import vista.PanelVerActividadesMonitor;
import vista.VistaMenuPrincipalMonitor;

/**
 * Controlador que muestra al monitor todas las actividades registradas en el sistema,
 * independientemente de si las ha creado él o no.
 * Recupera los datos desde la base de datos y actualiza el panel principal con la vista correspondiente.
 * 
 * Esta funcionalidad permite a los monitores consultar la planificación global de actividades.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorVerActividadesMonitor implements ActionListener {
	
	private VistaMenuPrincipalMonitor vista;
	
	public ControladorVerActividadesMonitor(VistaMenuPrincipalMonitor vista) {
        this.vista = vista;
    }


	
	public void actionPerformed(ActionEvent e) {
		
		 AccesoBBDDLogin acceso = new AccesoBBDDLogin();
	        Connection conn = acceso.getConexion();

	        if (conn != null) {
	            try {
	                List<Actividad> actividades = acceso.obtenerTodasLasActividades(conn);
	                PanelVerActividadesMonitor panel = new PanelVerActividadesMonitor(actividades);
	                vista.cambiarPanel(panel);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            } finally {
	                acceso.terminarConexion(conn);
	            }
	        } else {
	            System.out.println("Error al conectar con la base de datos.");
	        }
	    }
		
		
	}


