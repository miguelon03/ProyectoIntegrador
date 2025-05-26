package control;

import modelo.Actividad;
import vista.PanelEditarActividad;
import vista.VistaMenuPrincipalMonitor;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador que gestiona el evento del botón "Editar" en el panel de actividades del monitor.
 * Al pulsar el botón, recupera la actividad seleccionada de la lista y abre el panel
 * {@link vista.PanelEditarActividad} para permitir la edición de la fecha y hora de la actividad.
 * Se asegura de que haya una actividad seleccionada antes de realizar la acción.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorEditarActividadMonitor implements ActionListener {

    private JList<String> listaVisual;
    private List<Actividad> actividades;
    private JButton botonEditar;
    private VistaMenuPrincipalMonitor vista;

    public ControladorEditarActividadMonitor(JList<String> listaVisual, List<Actividad> actividades, JButton botonEditar, VistaMenuPrincipalMonitor vista) {
        this.listaVisual = listaVisual;
        this.actividades = actividades;
        this.botonEditar = botonEditar;
        this.vista = vista;
        botonEditar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int seleccion = listaVisual.getSelectedIndex();

        if (seleccion == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona una actividad para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener actividad seleccionada
        Actividad actividadSeleccionada = actividades.get(seleccion);

        // Crear el panel de edición y asociar su controlador
        PanelEditarActividad panelEditar = new PanelEditarActividad(actividadSeleccionada);
        new ControladorGuardarCambiosActividad(panelEditar, vista);

        // Cambiar el panel mostrado
        vista.cambiarPanel(panelEditar);
    }
}
