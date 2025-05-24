package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Usuario;
import vista.VistaEleccion;
import vista.VistaMenuPrincipalUsuario;

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

