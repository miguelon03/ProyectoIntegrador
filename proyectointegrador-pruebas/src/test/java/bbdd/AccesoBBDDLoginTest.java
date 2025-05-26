package bbdd;

import modelo.Usuario;
import modelo.Sala;
import modelo.Actividad;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccesoBBDDLoginTest {

    private static AccesoBBDDLogin acceso;
    private static Connection conn;

    @BeforeAll
    public static void setup() {
        acceso = new AccesoBBDDLogin();
        conn = acceso.getConexion();
        assertNotNull(conn, "La conexión a la base de datos no debería ser null");
    }

    @AfterAll
    public static void teardown() {
        if (conn != null) {
            acceso.terminarConexion(conn);
        }
    }

    @Test
    public void testComprobarLoginCorrecto() {
        boolean resultado = acceso.comprobarLogIn(conn, "1", "vivamarruecos");
        assertTrue(resultado, "El login con credenciales correctas debería funcionar");
    }

    @Test
    public void testObtenerUsuarioCompleto() {
        Usuario usuario = acceso.obtenerUsuarioCompleto(conn, "1");
        assertNotNull(usuario, "El usuario no debería ser null");
        assertEquals("Pedro Sanchez", usuario.getNombre() + " " + usuario.getApellidos());
    }

    @Test
    public void testObtenerTodasLasSalas() {
        List<Sala> salas = acceso.obtenerTodasLasSalas(conn);
        assertNotNull(salas, "La lista de salas no debe ser null");
        assertFalse(salas.isEmpty(), "La lista de salas no debe estar vacía");
    }

    @Test
    public void testObtenerActividadesDeMonitor() {
        List<Actividad> actividades = acceso.obtenerActividadesDeMonitor(conn, 3);
        assertNotNull(actividades, "La lista de actividades no debe ser null");
        assertFalse(actividades.isEmpty(), "El monitor debería tener al menos una actividad");
    }
    
    @Test
    public void testInsertarNuevaActividad() {
        
        String nombreActividad = "Test Actividad Unitaria";
        String fecha = "2025-06-30";
        String hora = "10:00:00";
        int idMonitor = 3; 
        int idSala = 1;     
        int capacidad = 10;

        boolean resultado = acceso.insertarNuevaActividad(conn, nombreActividad, fecha, hora, idMonitor, idSala, capacidad);
        assertTrue(resultado, "La actividad debería insertarse correctamente");
    }
    
    @Test
    public void testInscribirUsuarioEnActividad() {
        int idUsuario = 6;   
        int idActividad = 1; 

        boolean resultado = acceso.inscribirUsuarioEnActividad(conn, idUsuario, idActividad);
        assertTrue(resultado, "El usuario debería poder inscribirse si no está inscrito y hay plazas");

        
        acceso.borrarInscripcionUsuario(conn, idUsuario, idActividad);
    }
    
    @Test
    public void testBorrarActividadPorId() {
        // Primero insertamos una actividad para luego borrarla
        String nombre = "Actividad temporal";
        String fecha = "2025-07-01";
        String hora = "12:00:00";
        int monitor = 3;
        int sala = 1;
        int capacidad = 5;

        boolean insertado = acceso.insertarNuevaActividad(conn, nombre, fecha, hora, monitor, sala, capacidad);
        assertTrue(insertado, "Debe insertarse una actividad de prueba");

        // Recuperamos su ID para borrarla
        String sql = "SELECT ID_ACTIVIDAD FROM actividad WHERE NOM_ACTIVIDAD = ? AND FECHA = ? AND HORA = ?";
        int idActividad = -1;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, fecha);
            stmt.setString(3, hora);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idActividad = rs.getInt("ID_ACTIVIDAD");
            }
            rs.close();
        } catch (Exception e) {
            fail("Error al recuperar la actividad insertada: " + e.getMessage());
        }

        assertTrue(idActividad != -1, "La actividad debería existir para poder borrarla");

        boolean borrado = acceso.borrarActividadPorId(conn, idActividad);
        assertTrue(borrado, "La actividad debería haberse borrado correctamente");
    }
    
    @Test
    public void testObtenerActividadesInscritasDeUsuario() {
        int idUsuario = 6;

        ArrayList<Actividad> actividades = acceso.obtenerActividadesInscritasDeUsuario(conn, idUsuario);
        assertNotNull(actividades, "La lista no debe ser nula");
        
        System.out.println("Actividades inscritas para usuario " + idUsuario + ": " + actividades.size());
    }
    
    @Test
    public void testExisteActividadEnMismoHorario() {
      
        String nombre = "Partido de baloncesto";
        String fecha = "2025-04-16";
        String hora = "11:30:00";

        boolean existe = acceso.existeActividadEnMismoHorario(conn, nombre, fecha, hora);
        assertTrue(existe, "Esta actividad ya debería existir en ese horario");
    }
}