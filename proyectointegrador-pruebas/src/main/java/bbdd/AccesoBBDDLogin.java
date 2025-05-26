package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;

import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.Sala;
import java.sql.PreparedStatement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase se encarga de gestionar la conexión con la base de datos,
 * así como realizar consultas relacionadas con usuarios, actividades y salas.
 * Proporciona métodos para loguear usuarios, gestionar inscripciones
 * y consultar información de actividades y salas.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class AccesoBBDDLogin {
	
	 private String driver = "com.mysql.cj.jdbc.Driver";
		
		private String url="jdbc:mysql://localhost/proyectointegradorpruebas";
		
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
		
		/*
		 * Método para comprobar si el login es correcto
		 * -llamado en ControladorLogin
		 */
		
		public boolean comprobarLogIn(Connection conn, String IdUsuario, String password) {
			
			//booleano que devuelve si el login se completó correctamente o no
			boolean logueado = false;
			
			try {
				//creamos un statement
				Statement stmt= conn.createStatement();
				//ejecutamos la consulta
				//el ID_Usuario de la base de datos debe ser igual a la variable IdUsuario que se introduce en el login
				//la contraseña  de la base de datos debe ser igual a la variable password que se introduce en el login
				ResultSet resultados = stmt.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO = '"+IdUsuario+"' AND PASSWORD = '"+password+"'");
				
				//si hay resultados, el usuario se ha logueado correctamente (logueado = verdadero)
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
		
		//Método para terminar la conexion
		public void terminarConexion(Connection conn) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		//MÉTODO PARA OBTENER EL USUARIO COMPLETO
		//-llamado en ControladorLogin
		public Usuario obtenerUsuarioCompleto(Connection conn, String idUsuario) {
		    Usuario usuario = null;
		    try {
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO = '" + idUsuario + "'");
		        if (rs.next()) {
		            //Creamos un objeto usuario con los datos de la base de datos
		        	usuario = new Usuario(
		                rs.getInt("ID_USUARIO"),
		                rs.getString("N_MATRICULA"),
		                rs.getString("NOM"),
		                rs.getString("APE"),
		                rs.getString("CICLO"),
		                rs.getString("PASSWORD")
		            );
		        }
		        rs.close();
		        stmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return usuario;
		}
		
		
		
		//MÉTODO PARA OBTENER LAS ACTIVIDADES QUE PERTENECEN A UN MONITOR
		//-llamado en ControladorMisActividadesMonitor para obtener la lista de actividades
		//-llamado en ControladorGuardarCambiosActividad  para obetener la lista de actividades una vez guardados los cambios
		public ArrayList<Actividad> obtenerActividadesDeMonitor(Connection conn, int idMonitor) {
		    ArrayList<Actividad> listaActividades = new ArrayList<>();

		    try {
		        String sql = "SELECT a.*, s.TIPO_SALA, s.CAPACIDAD, u.N_MATRICULA, u.NOM, u.APE, u.CICLO, u.PASSWORD " +
		                     "FROM actividad a " +
		                     "JOIN sala s ON a.ID_SALA = s.ID_SALA " +
		                     "JOIN usuario u ON a.ID_MONITOR = u.ID_USUARIO " +
		                     "WHERE a.ID_MONITOR = ?";
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, idMonitor);
		        ResultSet rs = pstmt.executeQuery();

		        while (rs.next()) {
		        	
		        	 String horaCompleta = rs.getString("HORA");      // Ej: "10:00:00"
		        	 String horaFormateada = horaCompleta.substring(0, 5); // Resultado: "10:00"
		        	 
		        	 String fechaBD= rs.getString("FECHA");
		        	 //Buscado en internet para poner la fecha en formato dia/mes/año
		        	 LocalDate fecha = LocalDate.parse(fechaBD);
		        	 String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            // Crear Sala
		            Sala sala = new Sala(
		                rs.getInt("ID_SALA"),
		                rs.getString("TIPO_SALA"),
		                rs.getInt("CAPACIDAD")
		            );

		            // Crear Usuario (monitor)
		            Usuario usuario = new Usuario(
		                rs.getInt("ID_MONITOR"),
		                rs.getString("N_MATRICULA"),
		                rs.getString("NOM"),
		                rs.getString("APE"),
		                rs.getString("CICLO"),
		                rs.getString("PASSWORD")
		            );

		            // Crear Actividad
		            Actividad actividad = new Actividad(
		                rs.getInt("ID_ACTIVIDAD"),
		                rs.getString("NOM_ACTIVIDAD"),
		                rs.getInt("N_MAX_ALUMNOS"),
		                fechaFormateada,
		                horaFormateada,
		                usuario.getNombre() + " " + usuario.getApellidos(), // campo monitor
		                sala,
		                usuario
		            );

		            listaActividades.add(actividad);
		        }

		        rs.close();
		        pstmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return listaActividades;
		}
		
		
		//MÉTODO PARA OBTENER TODAS LAS ACTIVIDADES
		//-llamado en los controladores de VerActividades para crear la lista de actividades
		public ArrayList<Actividad> obtenerTodasLasActividades(Connection conn) {
		    ArrayList<Actividad> listaActividades = new ArrayList<>();

		    try {
		        String sql = "SELECT a.*, s.TIPO_SALA, s.CAPACIDAD, " +
		                     "u.N_MATRICULA, u.NOM, u.APE, u.CICLO, u.PASSWORD " +
		                     "FROM actividad a " +
		                     "JOIN sala s ON a.ID_SALA = s.ID_SALA " +
		                     "JOIN usuario u ON a.ID_MONITOR = u.ID_USUARIO";
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            String fechaBD = rs.getString("FECHA");
		            LocalDate fecha = LocalDate.parse(fechaBD);
		            String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		            String horaFormateada = rs.getString("HORA").substring(0, 5);

		            Sala sala = new Sala(
		                rs.getInt("ID_SALA"),
		                rs.getString("TIPO_SALA"),
		                rs.getInt("CAPACIDAD")
		            );

		            Usuario usuario = new Usuario(
		                rs.getInt("ID_MONITOR"),
		                rs.getString("N_MATRICULA"),
		                rs.getString("NOM"),
		                rs.getString("APE"),
		                rs.getString("CICLO"),
		                rs.getString("PASSWORD")
		            );

		            Actividad actividad = new Actividad(
		                rs.getInt("ID_ACTIVIDAD"),
		                rs.getString("NOM_ACTIVIDAD"),
		                rs.getInt("N_MAX_ALUMNOS"),
		                fechaFormateada,
		                horaFormateada,
		                usuario.getNombre() + " " + usuario.getApellidos(),
		                sala,
		                usuario
		            );

		            listaActividades.add(actividad);
		        }

		        rs.close();
		        stmt.close();

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return listaActividades;
		}

		
		
		//MÉTODO PARA INSICRIBIR AL USUARIO EN UNA ACTIVIDAD
		//-llamado en controladorInscripcion
		public boolean inscribirUsuarioEnActividad(Connection conn, int idUsuario, int idActividad) {
		    boolean exito = false;

		    try {
		        // 1. Verificar si ya está inscrito
		        //Consultamos CUANTAS veces esta ese usuario inscrito en la actividad
		    	String check = "SELECT COUNT(*) FROM INSCRIPCION WHERE ID_USUARIO = ? AND ID_ACTIVIDAD = ?";
		        PreparedStatement psCheck = conn.prepareStatement(check);
		        psCheck.setInt(1, idUsuario);
		        psCheck.setInt(2, idActividad);
		        ResultSet rs = psCheck.executeQuery();
		        rs.next();
		        //Si en esa consulta hay una fila, el usuario YA está inscrito y se devuelve false.
		        if (rs.getInt(1) > 0) {
		            System.out.println("Ya está inscrito.");
		            rs.close();
		            psCheck.close();
		            return false;
		        }
		        rs.close();
		        psCheck.close();

		        // 2. Verificar si hay plazas disponibles
		        //Obtenemos el valor del numero maximo de alumnos para esa actividad y lo definimos como el aforo
		        String aforo = "SELECT N_MAX_ALUMNOS FROM ACTIVIDAD WHERE ID_ACTIVIDAD = ?";
		        PreparedStatement psAforo = conn.prepareStatement(aforo);
		        psAforo.setInt(1, idActividad);
		        ResultSet rsAforo = psAforo.executeQuery();
		        int max = 0;
		        if (rsAforo.next()) {
		            max = rsAforo.getInt("N_MAX_ALUMNOS");
		        }
		        rsAforo.close();
		        psAforo.close();

		        //Contamos cuantas inscripciones hay actualmente de esa actividad
		        String inscritos = "SELECT COUNT(*) FROM INSCRIPCION WHERE ID_ACTIVIDAD = ?";
		        PreparedStatement psContador = conn.prepareStatement(inscritos);
		        psContador.setInt(1, idActividad);
		        ResultSet rsContador = psContador.executeQuery();
		        rsContador.next();
		        int actuales = rsContador.getInt(1);
		        rsContador.close();
		        psContador.close();

		        //Si las inscripciones son mayores (por fallo) o iguales al máximo, no se permite la inscripción
		        if (actuales >= max) {
		            System.out.println("No hay plazas disponibles.");
		            return false;
		        }

		        // 2. Insertar inscripción con fecha si no está inscrito y hay plazas
		        //Insertamos los datos en inscrpcion
		        String insert = "INSERT INTO INSCRIPCION (ID_USUARIO, ID_ACTIVIDAD, FECHA_INSCRIPCION) VALUES (?, ?, ?)";
		        PreparedStatement psInsert = conn.prepareStatement(insert);
		        psInsert.setInt(1, idUsuario);
		        psInsert.setInt(2, idActividad);
		        psInsert.setDate(3, Date.valueOf(LocalDate.now()));

		       //Si la inscripción fue exitosa, es decir , se ha insertado una fila, se muestra mensaje y se devuelve true
		        int filas = psInsert.executeUpdate();
		        exito = filas > 0;
		        psInsert.close();

		        if (exito) {
		            System.out.println("Inscripción realizada con éxito.");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return exito;
		}
		
		
		
		//MÉTODO PAR OBTENER LAS ACTIVIDADES EN LA QUE SE HA INSCRITO UN USUARIO
		//-llamado en ControladorMisActividadesUsuario para crear la lista de actividades
		public ArrayList<Actividad> obtenerActividadesInscritasDeUsuario(Connection conn, int idUsuario) {
		    ArrayList<Actividad> listaActividades = new ArrayList<>();

		    try {
		        String sql = "SELECT a.*, s.TIPO_SALA, s.CAPACIDAD, " +
		                     "u.N_MATRICULA, u.NOM, u.APE, u.CICLO, u.PASSWORD " +
		                     "FROM inscripcion i " +
		                     "JOIN actividad a ON i.ID_ACTIVIDAD = a.ID_ACTIVIDAD " +
		                     "JOIN sala s ON a.ID_SALA = s.ID_SALA " +
		                     "JOIN usuario u ON a.ID_MONITOR = u.ID_USUARIO " +
		                     "WHERE i.ID_USUARIO = ?";
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        stmt.setInt(1, idUsuario);

		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            String fechaBD = rs.getString("FECHA");
		            LocalDate fecha = LocalDate.parse(fechaBD);
		            String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            String horaFormateada = rs.getString("HORA").substring(0, 5);

		            Sala sala = new Sala(
		                rs.getInt("ID_SALA"),
		                rs.getString("TIPO_SALA"),
		                rs.getInt("CAPACIDAD")
		            );

		            Usuario usuario = new Usuario(
		                rs.getInt("ID_MONITOR"),
		                rs.getString("N_MATRICULA"),
		                rs.getString("NOM"),
		                rs.getString("APE"),
		                rs.getString("CICLO"),
		                rs.getString("PASSWORD")
		            );

		            Actividad actividad = new Actividad(
		                rs.getInt("ID_ACTIVIDAD"),
		                rs.getString("NOM_ACTIVIDAD"),
		                rs.getInt("N_MAX_ALUMNOS"),
		                fechaFormateada,
		                horaFormateada,
		                usuario.getNombre() + " " + usuario.getApellidos(),
		                sala,
		                usuario
		            );

		            listaActividades.add(actividad);
		        }

		        rs.close();
		        stmt.close();

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return listaActividades;
		}
		
		
		//MÉTODO PARA BORRAR LA INSCRIPCION DE UN USUARIO EN UNA ACTIVIDAD
		//-llamado en ControladorBorrarInscripcion
		public boolean borrarInscripcionUsuario(Connection conn, int idUsuario, int idActividad) {
		    boolean exito = false;

		    try {
		        String sql = "DELETE FROM INSCRIPCION WHERE ID_USUARIO = ? AND ID_ACTIVIDAD = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setInt(1, idUsuario);
		        ps.setInt(2, idActividad);

		        int filas = ps.executeUpdate();
		        exito = filas > 0;
		        ps.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return exito;
		}
		
		
		
		//MÉTODO PARA OBTENER UNA LISTA DE LAS SALAS
		//-llamado en los controladores de VerSalasMonitor y VerSalasUsuario para crear
		//la lista de salas
		public List<Sala> obtenerTodasLasSalas(Connection conn) {
		    List<Sala> salas = new ArrayList<>();

		    try {
		        String sql = "SELECT * FROM SALA";
		        PreparedStatement stmt = conn.prepareStatement(sql);
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            Sala sala = new Sala(
		                rs.getInt("ID_SALA"),
		                rs.getString("TIPO_SALA"),
		                rs.getInt("CAPACIDAD")
		            );
		            salas.add(sala);
		        }

		        rs.close();
		        stmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return salas;
		}
		
		
		
		//MÉTODO PARA INSERTAR UNA ACTIVIDAD NUEVA EN LA BASE DE DATOS
		//-llamado en ControladorBotonAnadir
		public boolean insertarNuevaActividad(Connection conn, String nombreActividad, String fecha, String hora, int idMonitor, int idSala, int capacidad) {
		    String sql = "INSERT INTO actividad (NOM_ACTIVIDAD, FECHA, HORA, ID_MONITOR, ID_SALA, N_MAX_ALUMNOS) VALUES (?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, nombreActividad);
		        stmt.setString(2, fecha);             // formato: yyyy-MM-dd
		        stmt.setString(3, hora);              // formato: HH:mm:ss
		        stmt.setInt(4, idMonitor);
		        stmt.setInt(5, idSala);
		        stmt.setInt(6, capacidad);            // <- aquí usamos el argumento recibido

		        return stmt.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		
		//Método para comprobar si un mismo tipo de actividad está duplicada
		//-llamado en ControladorBotonAnadir
		public boolean existeActividadEnMismoHorario(Connection conn, String nombreActividad, String fecha, String hora) {
		    boolean existe = false;

		    //contamos cuantas actividades coindicen con la actvidad que se pasa como parámetro
		    String sql = """
		        SELECT COUNT(*) AS total
		        FROM actividad
		        WHERE NOM_ACTIVIDAD = ? AND FECHA = ? AND HORA = ?
		    """;

		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, nombreActividad);
		        stmt.setString(2, fecha); // formato: "yyyy-MM-dd"
		        stmt.setString(3, hora);  // formato: "HH:mm:ss"

		        ResultSet rs = stmt.executeQuery();
		       //si el resultado de esa cuenta existe ye es mayor que cero significa que la actividad
		       //que se quiere crear ya existe
		        if (rs.next() && rs.getInt("total") > 0) {
		            existe = true;
		        }

		        rs.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return existe;
		}

		
		//MÉTODO PARA OBTENER LA SALA DE CADA ACTIVIDAD
		//-llamado en los métodos del panelNuevaActividad
		public Sala obtenerSalaPorNombreActividad(Connection conn, String nombreActividad) {
		    Sala sala = null;
		    try {
		       //unimos las tablas actividad y sala para obtener la sala donde se realiza
		       //la actividad que pasamos como parámetro.
		       //con LIMIT 1 nos aseguramos de que solo se devuelva una sala aunque haya más
		    	//actividades con ese nombre.
		    	String sql = """
		            SELECT s.ID_SALA, s.TIPO_SALA, s.CAPACIDAD
		            FROM actividad a
		            JOIN sala s ON a.ID_SALA = s.ID_SALA
		            WHERE a.NOM_ACTIVIDAD = ?
		            LIMIT 1
		        """;

		        PreparedStatement stmt = conn.prepareStatement(sql);
		        stmt.setString(1, nombreActividad);
		        ResultSet rs = stmt.executeQuery();

		        //se crea un objeto sala con los resultados
		        if (rs.next()) {
		            sala = new Sala(
		                rs.getInt("ID_SALA"),
		                rs.getString("TIPO_SALA"),
		                rs.getInt("CAPACIDAD")
		            );
		        }

		        rs.close();
		        stmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return sala;
		}
		
		
	


		//MÉTODO PARA BORRAR UNA ACTIVIDAD POR ID DE ACTIVIDAD
		//-llamado desde ControladorBorrar
		public boolean borrarActividadPorId(Connection conn, int idActividad) {
		    String sql = "DELETE FROM actividad WHERE ID_ACTIVIDAD = ?";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, idActividad);
		        return stmt.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		
		//MÉTODO PARA EDITAR LA FECHA Y HORA DE LA ACTIVIDAD
		//-llamado en ControladorGuardarCambiosActividad al editar una actividad
		public boolean actualizarFechaHoraActividad(Connection conn, int idActividad, String nuevaFecha, String nuevaHora) {
		    /* Se actualizan los campos de fecha y hora con los parámetros nuevaFecha y nuevaHora
		     * de la actividad con el id pasado como parámetro
		     */
			
			String sql = "UPDATE actividad SET FECHA = ?, HORA = ? WHERE ID_ACTIVIDAD = ?";
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, nuevaFecha);
		        stmt.setString(2, nuevaHora);
		        stmt.setInt(3, idActividad);
		        return stmt.executeUpdate() > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		
		//MÉTODO PARA COMRPOBRAR SI EL MONITOR TIENE CUALQUIER ACTIVIDAD EN ESE HORARIO AL CREAR NUEVA ACTIVIDAD
		//-llamado en controladorBotonAnadir para comprobar si al monitor se le solapan actividades
		public boolean monitorTieneActividadEnHorario(Connection conn, int idMonitor, String fecha, String hora) {
		    String sql = "SELECT COUNT(*) FROM actividad WHERE ID_MONITOR = ? AND FECHA = ? AND HORA = ?";//cuenta las filas
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, idMonitor);
		        stmt.setString(2, fecha);
		        stmt.setString(3, hora);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next() && rs.getInt(1) > 0) {
		            return true; // Ya tiene una actividad en ese horario
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}
		
		
		//MÉTODO PARA COMPROBAR SI EL MONITOR TIENE CUALQUIER ACTIVIDAD EN ESE HORARIO AL CREAR NUEVA ACTIVIDAD EXCLuYENDO LA ACTIVIDAD QUE SE ESTá EDITANDO
		//-llamado en el controlador del botón guardar cambios para comprobar si el monitor
		//tiene otra actividad en ese horario pero EXCLUYENDO de la comprobación la actividad actual que se está editando
		public boolean monitorTieneOtraActividadEnHorario(Connection conn, int idMonitor, String fecha, String hora, int idActividadActual) {
		    String sql = """
		        SELECT COUNT(*) FROM actividad
		        WHERE ID_MONITOR = ? AND FECHA = ? AND HORA = ? AND ID_ACTIVIDAD != ? 
		    """;
		    //El id_actividad no puede ser igual al de la actividad que se está editando porque si no siempre
		    //habría una colisión consigo misa.
		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, idMonitor);
		        stmt.setString(2, fecha);
		        stmt.setString(3, hora);
		        stmt.setInt(4, idActividadActual);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next() && rs.getInt(1) > 0) {
		            return true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}



		
		
	



}
