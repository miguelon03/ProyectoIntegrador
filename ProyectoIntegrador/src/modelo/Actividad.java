package modelo;

public class Actividad {
	
	    private int idActividad;
	    private String nombreActividad;
	    private int numMaxParticipantes;
	    private String hora;
	    private String monitor;
	    private Sala sala; 
	    private Usuario usuario; 
	    private String fecha;

	    // Constructor
	    public Actividad(int idActividad, String nombreActividad, int numMaxParticipantes,String fecha, String hora, String monitor, Sala sala, Usuario usuario) {
	        this.idActividad = idActividad;
	        this.nombreActividad = nombreActividad;
	        this.numMaxParticipantes = numMaxParticipantes;
	        this.fecha=fecha;
	        this.hora=hora;
	        this.monitor = monitor;
	        this.sala = sala;
	        this.usuario = usuario;
	    }

	    // Getters y setters
	    public int getIdActividad() {
	        return idActividad;
	    }

	    public void setIdActividad(int idActividad) {
	        this.idActividad = idActividad;
	    }

	    public String getNombreActividad() {
	        return nombreActividad;
	    }

	    public void setNombreActividad(String nombreActividad) {
	        this.nombreActividad = nombreActividad;
	    }

	    public int getNumMaxParticipantes() {
	        return numMaxParticipantes;
	    }

	    public void setNumMaxParticipantes(int numMaxParticipantes) {
	        this.numMaxParticipantes = numMaxParticipantes;
	    }
	    
	    public String getFecha() {
	        return fecha;
	    }

	    public void setFecha(String fecha) {
	        this.fecha = fecha;
	    }
	    
	    public String getHora() {
	        return hora;
	    }

	    public void setHora(String hora) {
	        this.hora = hora;
	    }

	    public String getMonitor() {
	        return monitor;
	    }

	    public void setMonitor(String monitor) {
	        this.monitor = monitor;
	    }

	    public Sala getSala() {
	        return sala;
	    }

	    public void setSala(Sala sala) {
	        this.sala = sala;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(Usuario usuario) {
	        this.usuario = usuario;
	    }

}
