package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import modelo.Usuario;
import vista.VistaMenuPrincipalMonitor;
import vista.PanelMisActividadesMonitor;

public class ControladorMisActividadesMonitor implements ActionListener {

    private VistaMenuPrincipalMonitor vista;
    private String idUsuario; // el usuario logueado (monitor)
    private AccesoBBDDLogin accesoBBDD;

    public ControladorMisActividadesMonitor(VistaMenuPrincipalMonitor vista, String idUsuario) {
        this.vista = vista;
        this.idUsuario = idUsuario;
        this.accesoBBDD = new AccesoBBDDLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. Obtener conexión
        Connection conn = accesoBBDD.getConexion();
        if (conn != null) {
            try {
                // 2. Obtener las actividades del monitor
                List<Actividad> actividades = accesoBBDD.obtenerActividadesDeMonitor(conn, Integer.parseInt(idUsuario));

                // 3. Crear el panel con esas actividades
                PanelMisActividadesMonitor panel = new PanelMisActividadesMonitor(actividades);

                // 4. Mostrarlo en la ventana principal
                vista.cambiarPanel(panel);

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                // 5. Cerrar conexión
                accesoBBDD.terminarConexion(conn);
            }
        } else {
            System.out.println("No se pudo establecer conexión a la base de datos.");
        }
    }
}


