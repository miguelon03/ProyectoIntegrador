package control;

import bbdd.AccesoBBDDLogin;
import modelo.Actividad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class ControladorBorrar implements ActionListener {

    private JButton boton;
    private JList<String> listaVisual;
    private List<Actividad> actividades;
    private DefaultListModel<String> modeloLista;

    public ControladorBorrar(JButton boton, JList<String> listaVisual, List<Actividad> actividades, DefaultListModel<String> modeloLista) {
        this.boton = boton;
        this.listaVisual = listaVisual;
        this.actividades = actividades;
        this.modeloLista = modeloLista;
        boton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int seleccion = listaVisual.getSelectedIndex();

        //SI EL MONITOR NO HA SELECCIONADO NINGUNA ACTIVIDAD
        if (seleccion == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona una actividad para borrar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Actividad actividadSeleccionada = actividades.get(seleccion);
        int idActividad = actividadSeleccionada.getIdActividad();

        //MENSAJE DE CONFIRMACIÓN
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres borrar esta actividad?", "Confirmación", JOptionPane.YES_NO_OPTION);

        //SI EL MONITOR CONFIRMA
        if (confirmacion == JOptionPane.YES_OPTION) {
            AccesoBBDDLogin acceso = new AccesoBBDDLogin();
            try (Connection conn = acceso.getConexion()) {
                boolean borrada = acceso.borrarActividadPorId(conn, idActividad);

                if (borrada) {
                    actividades.remove(seleccion);
                    modeloLista.remove(seleccion);
                    JOptionPane.showMessageDialog(null, "Actividad borrada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo borrar la actividad.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
