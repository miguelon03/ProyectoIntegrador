package control;

import bbdd.AccesoBBDDLogin;
import modelo.Usuario;
import vista.PanelDatosPersonales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ControladorCambiarContrasena implements ActionListener {

    private PanelDatosPersonales panel;
    private Usuario usuario;

    public ControladorCambiarContrasena(PanelDatosPersonales panel, Usuario usuario) {
        this.panel = panel;
        this.usuario = usuario;
        this.panel.getBtnCambiar().addActionListener(this); // Asociamos el botón
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actual = new String(panel.getCampoActual().getPassword());
        String nueva = new String(panel.getCampoNueva().getPassword());
        String confirmar = new String(panel.getCampoConfirmar().getPassword());

        //Si la contraseña actual introducida no es 
        if (!actual.equals(usuario.getPassword())) {
            JOptionPane.showMessageDialog(panel, "La contraseña actual no es correcta");
        } else if (!nueva.equals(confirmar)) {
            JOptionPane.showMessageDialog(panel, "Las nuevas contraseñas no coinciden");
        } else if (nueva.isBlank()) {
            JOptionPane.showMessageDialog(panel, "La nueva contraseña no puede estar vacía");
        } else {
            try (Connection conn = new AccesoBBDDLogin().getConexion()) {
                String sql = "UPDATE usuario SET PASSWORD = ? WHERE ID_USUARIO = ?";
                var stmt = conn.prepareStatement(sql);
                stmt.setString(1, nueva);
                stmt.setInt(2, usuario.getIdUsuario());
                int filas = stmt.executeUpdate();
                stmt.close();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(panel, "Contraseña actualizada correctamente");
                    usuario.setPassword(nueva);
                    panel.limpiarCamposContrasena();//una vez actualiza se limpian los campos
                } else {
                    JOptionPane.showMessageDialog(panel, "No se pudo actualizar la contraseña");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error al conectar con la base de datos");
            }
        }
    }
}
