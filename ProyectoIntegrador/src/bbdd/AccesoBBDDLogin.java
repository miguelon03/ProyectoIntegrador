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
		public boolean comrobarLogIn(Connection conn, String IdUsuario, String password) {
			
			//booleano que devuelve si el login se completó correctamente o no
			boolean logueado = false;
			
			try {
				//creamos un statement
				Statement stmt= conn.createStatement();
				//ejecutamos la consulta
				ResultSet resultados = stmt.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO = '"+IdUsuario+"' AND PASSWORD = '"+password+"'");
				
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
		
		//METODO PARA OBTENER EL CICLO DEL USUARIO
		public String obtenerCicloUsuario(Connection conn, String IdUsuario) {
		    String ciclo = null;
		    try {
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT CICLO FROM usuario WHERE ID_USUARIO = '" + IdUsuario + "'");
		        if (rs.next()) {
		            ciclo = rs.getString("CICLO");
		        }
		        rs.close();
		        stmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return ciclo;
		}
		
		
		
		//MÉTODO PARA OBTENER EL USUARIO COMPLETO
		public Usuario obtenerUsuarioCompleto(Connection conn, String idUsuario) {
		    Usuario usuario = null;
		    try {
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE ID_USUARIO = '" + idUsuario + "'");
		        if (rs.next()) {
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

		        //Contaos cuantas inscripciones hay actualmente de esa actividad
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
		
		
		//MÉTODO PARA COMOROBAR SI UNA ACTIVIDAD ESTÁ DUPLICADA
		public boolean existeActividadEnMismoHorario(Connection conn, String nombreActividad, String fecha, String hora) {
		    boolean existe = false;

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
		public Sala obtenerSalaPorNombreActividad(Connection conn, String nombreActividad) {
		    Sala sala = null;
		    try {
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
		public boolean actualizarFechaHoraActividad(Connection conn, int idActividad, String nuevaFecha, String nuevaHora) {
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

		
		
	



}
