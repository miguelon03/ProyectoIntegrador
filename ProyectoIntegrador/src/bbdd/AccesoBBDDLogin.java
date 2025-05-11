package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBBDDLogin {
	
	 private String driver = "com.mysql.cj.jdbc.Driver";
		
		private String url="jdbc:mysql://localhost/proyectointegradorbasededatos";
		
		/*
		 * jdbc -> tipo driver
		 * mysql -> tipo de servidor BD
		 * localhost -> donde está alojada
		 * tareauf10_login -> la BD a la que queremos acceder
		 * 
		 * */
		//usuario y contraseña del workbench
		private String usuario = "root";
		private String passwd = "1234";
		
		//método para comprobar la conexion a la bbdd
		public Connection getConexion() {
			Connection con = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, usuario, passwd);
				System.out.println("Conexión establecida");
				
			}catch (Exception e) {
				System.out.println("Error de conexión con la BBDD");
				e.printStackTrace();
			}	
			return con;
		}
		
		//método para comprobar si el login es correcto
		public boolean comrobarLogIn(Connection conn, String usuario, String password) {
			
			//booleano que devuelve si el login se completó correctamente o no
			boolean logueado = false;
			
			try {
				//creamos un statement
				Statement stmt= conn.createStatement();
				//ejecutamos la consulta
				ResultSet resultados = stmt.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO = '"+usuario+"' AND PASSWORD = '"+password+"'");
				
				//si hay resultados, el usuario se ha logueado correctamente (loguesdo = verdadero)
				if(resultados.next()) {
					logueado=true;
				}
				
				//cerramos los resultados y el statement
				resultados.close();
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return logueado;
			
		}
		
		//método para terminar la conexion
		public void terminarConexion(Connection conn) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

}
