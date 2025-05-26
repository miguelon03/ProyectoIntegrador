package control;

import modelo.Actividad;
import modelo.Usuario;
import vista.PanelVerActividadesUsuario;
import bbdd.AccesoBBDDLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 * Controlador que gestiona el proceso de inscripción de un usuario en una actividad.
 * Verifica si el usuario ya está inscrito o si hay plazas disponibles antes de realizar la inscripción
 * usando los métodos de {@link bbdd.AccesoBBDDLogin}.
 * 
 * Si la inscripción es válida, la registra en la base de datos y muestra un mensaje de confirmación.
 * En caso contrario, muestra los mensajes de error correspondientes.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorInscripcion implements ActionListener {

    private PanelVerActividadesUsuario panel;
    private Usuario usuario;

    public ControladorInscripcion(PanelVerActividadesUsuario panel, Usuario usuario) {
        this.panel = panel;
        this.usuario = usuario;
        this.panel.getBtnInscribirse().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int seleccion = panel.getListaActividades().getSelectedIndex();

        
        if (seleccion == -1) {
            JOptionPane.showMessageDialog(panel, "Por favor, selecciona una actividad.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Actividad> actividades = panel.getActividades();
        Actividad actividadSeleccionada = actividades.get(seleccion);

        int idUsuario = usuario.getIdUsuario();
        int idActividad = actividadSeleccionada.getIdActividad();

        AccesoBBDDLogin acceso = new AccesoBBDDLogin();
        try (Connection conn = acceso.getConexion()) {

            boolean exito = acceso.inscribirUsuarioEnActividad(conn, idUsuario, idActividad);

            if (exito) {
                JOptionPane.showMessageDialog(panel, "Inscripción realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "No se pudo realizar la inscripción.\nEs posible que ya estés inscrito o no haya plazas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
