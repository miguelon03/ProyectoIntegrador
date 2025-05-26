package modelo;

/**
 * Clase que representa a un usuario del sistema, que puede actuar como alumno o monitor.
 * 
 * Contiene información básica como el ID, número de matrícula, nombre, apellidos, ciclo al que pertenece
 * y contraseña. Esta clase se utiliza en todo el proyecto para gestionar tanto la autenticación
 * como la asignación de actividades a monitores o inscripciones de usuarios.
 * 
 * Es una clase fundamental para las operaciones de inicio de sesión, visualización de datos personales
 * y gestión de roles dentro de la aplicación.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class Usuario {
	   private int idUsuario;
	    private String numMatricula;
	    private String nombre;
	    private String apellidos;
	    private String ciclo;
	    private String password;

	    // Constructor
	    public Usuario(int idUsuario, String numMatricula, String nombre, String apellidos, String ciclo, String password) {
	        this.idUsuario = idUsuario;
	        this.numMatricula = numMatricula;
	        this.nombre = nombre;
	        this.apellidos = apellidos;
	        this.ciclo = ciclo;
	        this.password = password;
	    }

	    // Getters y setters
	    public int getIdUsuario() {
	        return idUsuario;
	    }

	    public void setIdUsuario(int idUsuario) {
	        this.idUsuario = idUsuario;
	    }

	    public String getNumMatricula() {
	        return numMatricula;
	    }

	    public void setNumMatricula(String numMatricula) {
	        this.numMatricula = numMatricula;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getApellidos() {
	        return apellidos;
	    }

	    public void setApellidos(String apellidos) {
	        this.apellidos = apellidos;
	    }

	    public String getCiclo() {
	        return ciclo;
	    }

	    public void setCiclo(String ciclo) {
	        this.ciclo = ciclo;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

}
