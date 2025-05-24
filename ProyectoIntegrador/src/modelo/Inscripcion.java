package modelo;

import java.time.LocalDate;

public class Inscripcion {
	private int idInscripcion;
    private Usuario usuario; // Puede ser idUsuario o similar
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
