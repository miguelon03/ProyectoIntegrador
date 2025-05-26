package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Usuario;
import vista.VistaEleccion;
import vista.VistaMenuPrincipalUsuario;

/**
 * Controlador que gestiona la acción del botón "Usuario" en la vista de elección.
 * Al pulsar el botón, se cierra la vista de elección y se abre el menú principal correspondiente al usuario.
 *
 * Este controlador se utiliza en {@link vista.VistaEleccion} y está pensado para usuarios que desean acceder como usuario normal.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorBotonUsuario implements ActionListener {

    private VistaEleccion vistaEleccion;
    private Usuario usuario;

    public ControladorBotonUsuario(VistaEleccion vistaEleccion, Usuario usuario) {
        this.vistaEleccion = vistaEleccion;
        this.usuario = usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vistaEleccion.dispose();  // Cierra la ventana actual
        VistaMenuPrincipalUsuario menuUsuario = new VistaMenuPrincipalUsuario(usuario);
        menuUsuario.hacerVisible();   // Muestra el menú principal para usuarios
    }
}

