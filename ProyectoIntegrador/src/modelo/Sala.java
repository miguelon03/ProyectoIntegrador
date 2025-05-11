package modelo;

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
