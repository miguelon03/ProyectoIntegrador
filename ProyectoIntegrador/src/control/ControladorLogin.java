package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import bbdd.AccesoBBDDLogin;
import vista.VistaLogin;
import vista.VistaMenuPrincipalMonitor;


public class ControladorLogin implements ActionListener {
	private VistaLogin ventLogin;
	private VistaMenuPrincipalMonitor ventPpalM;
	
	public ControladorLogin(VistaLogin ventLogin) {
		this.ventLogin=ventLogin;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Qué va a hacer este controlador
		
		//Pimero obtenemos el usuario y la contraseña
		String user = ventLogin.getUsuario();
		String password = new String (ventLogin.getPassword().getPassword());
		
		//Creamos un objeto acceso y conexion
		AccesoBBDDLogin acceso = new AccesoBBDDLogin();
		Connection conn = acceso.getConexion();
		
		// comprobamos si hay conexion y si el login es correcto en ese caso
		if (conn != null) {// Si la conexion no es nula

			// realizamos la comprobacion del login con el metodo
			boolean Login = acceso.comrobarLogIn(conn, user, password);

			// terminamos la conexion cuando se ha completado la consulta del login
			acceso.terminarConexion(conn);

			// comprobamos el login
			if (Login) {
				JOptionPane.showMessageDialog(ventLogin, "Inicio de sesión correcto");
				ventLogin.dispose();
				ventPpalM= new VistaMenuPrincipalMonitor("EurosportsClub");
				ventPpalM.hacerVisible();
			} else
			JOptionPane.showMessageDialog(ventLogin, "Contraseña incorrecta. Inténtalo de nuevo");

			// si el usuario se queda sin intentos

		} else
			JOptionPane.showMessageDialog(ventLogin, "Error de conexión con la BBDD");
		
	}

}
