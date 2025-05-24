package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.List;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import vista.PanelVerActividadesMonitor;
import vista.VistaMenuPrincipalMonitor;

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


