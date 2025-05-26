package modelo;

/**
 * Clase que representa una sala física donde se realizan las actividades.
 * 
 * Cada sala contiene un identificador único, un tipo descriptivo (como "Pista de baloncesto",
 * "Piscina polideportivo", etc.) y una capacidad máxima de alumnos. Esta clase se utiliza para asociar
 * las actividades con los espacios físicos disponibles en el centro.
 * 
 * Se usa principalmente en la creación y visualización de actividades, así como para restringir
 * las inscripciones de usuarios según el aforo disponible.
 * 
 * @author Antonio Alonso
 * @author Miguel De Pablo
 * @author Juan José González
 */
public class Sala {
	  private int idSala;
	    private String tipoSala;
	    private int capacidad;

	    // Constructor
	    public Sala(int idSala, String tipoSala, int capacidad) {
	        this.idSala = idSala;
	        this.tipoSala = tipoSala;
	        this.capacidad = capacidad;
	    }

	    // Getters y setters
	    public int getIdSala() {
	        return idSala;
	    }

	    public void setIdSala(int idSala) {
	        this.idSala = idSala;
	    }

	    public String getTipoSala() {
	        return tipoSala;
	    }

	    public void setTipoSala(String tipoSala) {
	        this.tipoSala = tipoSala;
	    }

	    public int getCapacidad() {
	        return capacidad;
	    }

	    public void setCapacidad(int capacidad) {
	        this.capacidad = capacidad;
	    }

}
