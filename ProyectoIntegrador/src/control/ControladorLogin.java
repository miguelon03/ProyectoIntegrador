package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import bbdd.AccesoBBDDLogin;
import modelo.Usuario;
import vista.VistaEleccion;
import vista.VistaLogin;
import vista.VistaMenuPrincipalMonitor;
import vista.VistaMenuPrincipalUsuario;

/**
 * Controlador encargado de gestionar el inicio de sesión de los usuarios en la aplicación.
 * 
 * Verifica las credenciales ingresadas a través de la interfaz de login,
 * consulta la base de datos mediante {@link AccesoBBDDLogin} y redirige al usuario
 * a la vista correspondiente dependiendo de su ciclo formativo (TAFD, DAW, etc.).
 * 
 * Si el usuario pertenece al ciclo TAFD, se muestra una pantalla de elección
 * que permite acceder como usuario o como monitor.
 * 
 * Actúa como puente
 * entre la vista {@link vista.VistaLogin} y el modelo {@link modelo.Usuario}.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class ControladorLogin implements ActionListener {
	private VistaLogin ventLogin;
	private VistaMenuPrincipalMonitor ventPpalM;
	private VistaMenuPrincipalUsuario ventPpalU;
	private VistaEleccion ventEleccion;
	
	public ControladorLogin(VistaLogin ventLogin) {
		this.ventLogin=ventLogin;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Qué va a hacer este controlador
		
		//Pimero obtenemos el usuario y la contraseña con los métodos get
		String user = ventLogin.getUsuario();
		String password = new String (ventLogin.getPassword().getPassword());
		
		//Creamos un objeto acceso y conexion
		AccesoBBDDLogin acceso = new AccesoBBDDLogin();
		Connection conn = acceso.getConexion();
		
		// comprobamos si hay conexion y si el login es correcto en ese caso
		if (conn != null) {// Si la conexion no es nula

			// realizamos la comprobacion del login con el metodo
			boolean Login = acceso.comprobarLogIn(conn, user, password);

			// terminamos la conexion cuando se ha completado la consulta del login
			acceso.terminarConexion(conn);

			// comprobamos el login
			if (Login) {
				JOptionPane.showMessageDialog(ventLogin, "Inicio de sesión correcto");
				
				 conn = acceso.getConexion();
				 
				 //Creamos un objeto usuario con el método de AccesoBBDDLogin
				 Usuario usuarioObj = acceso.obtenerUsuarioCompleto(conn, user);
				    acceso.terminarConexion(conn);
				
				ventLogin.dispose();
				
				/*Aquí necesito realizar una consulta de que si el ciclo del usuario es TAFAD
				 * se abra la pestaña de vista de eleccion ya que los usuarios de este ciclo pueden entrar a la app
				 * como usuarios normales o como monitores
				*/
				if ("TAFD".equalsIgnoreCase(usuarioObj.getCiclo())) {
			        // Si el ciclo es TAFD, mostrar la vista de elección
			        ventEleccion = new VistaEleccion(usuarioObj); // Si necesita el ID del usuario
			        ventEleccion.getBotonMonitor().addActionListener(new ControladorBotonMonitor(ventEleccion, usuarioObj));
			        ventEleccion.getBotonUsuario().addActionListener(new ControladorBotonUsuario(ventEleccion, usuarioObj));
			        ventEleccion.hacerVisible();
			    } else {
			        // Si no es TAFD, ir al menú principal normal de usuario
			        ventPpalU = new VistaMenuPrincipalUsuario(usuarioObj);
			        ventPpalU.hacerVisible();
			    }
				
				
			} else
			JOptionPane.showMessageDialog(ventLogin, "Contraseña incorrecta. Inténtalo de nuevo");

			

		} else
			JOptionPane.showMessageDialog(ventLogin, "Error de conexión con la BBDD");
		
	}

}
