package control;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;
import modelo.Sala;
import modelo.Usuario;
import vista.PanelNuevaActividad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * Controlador encargado de gestionar la lógica al pulsar el botón "Añadir" en el panel de nueva actividad.
 * Verifica que se haya seleccionado una actividad válida, que la fecha sea posterior a la actual,
 * que no haya otra actividad del mismo tipo en el mismo horario, y que el monitor no tenga ya
 * asignada otra actividad en ese momento. Si todo es correcto, inserta la nueva actividad en la base de datos.
 *
 * Se utiliza dentro de {@link vista.PanelNuevaActividad}.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorBotonAnadir implements ActionListener {

    private PanelNuevaActividad panel;
    private Usuario monitor;

    public ControladorBotonAnadir(PanelNuevaActividad panel, Usuario monitor) {
        this.panel = panel;
        this.monitor = monitor;
        panel.getBtnAnadir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actividad = panel.getActividad();
        String fecha = panel.getFechaCompleta();
        String hora = panel.getHora() + ":00";
        Sala sala = panel.getSalaAsociada();
        int capacidad = sala.getCapacidad();

        //El monitor debe seleccionar una actividad
        if (actividad == null || sala == null) {
            JOptionPane.showMessageDialog(panel, "Debe seleccionar una actividad válida.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
       //La fecha debe ser futura
        if (!panel.fechaEsValida()) {
            JOptionPane.showMessageDialog(panel, "La fecha no puede ser anterior a hoy.", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AccesoBBDDLogin acceso = new AccesoBBDDLogin();

        try (Connection conn = acceso.getConexion()) {
            //Comprobamos si existe una actividad cualquiera igual llamando al método de AccesoBBDD
        	boolean yaExiste = acceso.existeActividadEnMismoHorario(conn, actividad, fecha, hora);

        	if (yaExiste) {
                JOptionPane.showMessageDialog(panel, "Ya existe una actividad con ese nombre en esa fecha y hora.", "Actividad duplicada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Comprobamos al momitor se le solopa la actividad que queremos añadir con otra que dirige
        	//en la misma fecha y hora
        	if (acceso.monitorTieneActividadEnHorario(conn, monitor.getIdUsuario(), fecha, hora)) {
                JOptionPane.showMessageDialog(panel, "Tienes otra actividad con esta fecha y hora.", "Solapamiento", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insertar nueva actividad
            boolean insertada = acceso.insertarNuevaActividad(conn, actividad, fecha, hora, monitor.getIdUsuario(), sala.getIdSala(),capacidad);

            if (insertada) {
                JOptionPane.showMessageDialog(panel, "Actividad añadida correctamente.");
            } else {
                JOptionPane.showMessageDialog(panel, "No se pudo añadir la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
