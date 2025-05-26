package modelo;

import java.time.LocalDate;

/**
 * Clase que representa la inscripción de un usuario a una actividad deportiva.
 * 
 * Contiene la información necesaria para registrar qué usuario se ha inscrito a qué actividad
 * y en qué fecha. Sirve como vínculo entre las clases {@link Usuario} y {@link Actividad}.
 * 
 * Se utiliza para gestionar inscripciones, evitar duplicados, controlar el aforo de actividades
 * y mostrar las actividades a las que un usuario está apuntado.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class Inscripcion {
	private int idInscripcion;
    private Usuario usuario; 
    private Actividad actividad;
    private LocalDate fechaInscripcion;

    // Constructor
    public Inscripcion(int idInscripcion, Usuario usuario, Actividad actividad, LocalDate fechaInscripcion) {
    	this.idInscripcion= idInscripcion;
    	this.usuario=usuario;
    	this.actividad=actividad;
    	this.fechaInscripcion= fechaInscripcion;
      
    }

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

    // Getters y setters
   

}
