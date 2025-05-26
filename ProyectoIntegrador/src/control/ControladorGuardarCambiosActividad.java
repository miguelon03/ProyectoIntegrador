package control;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import vista.PanelEditarActividad;
import vista.VistaMenuPrincipalMonitor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * Controlador encargado de gestionar la acción de guardar los cambios editados
 * en una actividad existente, específicamente su fecha y hora.
 * Valida que la nueva fecha no sea anterior al día actual y que no exista conflicto
 * de horario con otra actividad del mismo monitor.
 * Si las validaciones se superan, actualiza la actividad en la base de datos
 * utilizando {@link bbdd.AccesoBBDDLogin} y actualiza el panel del monitor.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorGuardarCambiosActividad implements ActionListener {

    private PanelEditarActividad panel;
    private VistaMenuPrincipalMonitor vista;

    public ControladorGuardarCambiosActividad(PanelEditarActividad panel, VistaMenuPrincipalMonitor vista) {
        this.panel = panel;
        this.vista = vista;
        panel.getBtnGuardar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nuevaFecha = panel.getFechaCompleta();
        String nuevaHora = panel.getHora() + ":00";
        Actividad actividad = panel.getActividad();

        if (!panel.fechaEsValida()) {
            JOptionPane.showMessageDialog(panel, "La fecha no puede ser anterior a hoy.", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AccesoBBDDLogin acceso = new AccesoBBDDLogin();
        try (Connection conn = acceso.getConexion()) {

        	 boolean monitorOcupado = acceso.monitorTieneOtraActividadEnHorario(conn,actividad.getUsuario().getIdUsuario(),nuevaFecha,nuevaHora,actividad.getIdActividad());
        	 
        	 if (monitorOcupado) {
                 JOptionPane.showMessageDialog(panel, "Ya existe otra actividad en ese horario", "Conflicto", JOptionPane.ERROR_MESSAGE);
                 return;
             }
          


            boolean actualizado = acceso.actualizarFechaHoraActividad(conn, actividad.getIdActividad(), nuevaFecha, nuevaHora);

            if (actualizado) {
                JOptionPane.showMessageDialog(panel, "Actividad actualizada correctamente.");
                vista.cambiarPanel(new vista.PanelMisActividadesMonitor(acceso.obtenerActividadesDeMonitor(conn, actividad.getUsuario().getIdUsuario())));
            } else {
                JOptionPane.showMessageDialog(panel, "No se pudo actualizar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
