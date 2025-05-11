package modelo;

public class Inscripcion {
	private String actividad;
    private String id; // Puede ser idUsuario o similar

    // Constructor
    public Inscripcion(String actividad, String id) {
        this.actividad = actividad;
        this.id = id;
    }

    // Getters y setters
    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
