package api.entities;

public class Escritor {
	private String id;
	private int numero;
	private String description;
	
    public Escritor(int numero,String description) {
	this.numero = numero;
	this.description = description;
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public String getDescription() {
		return description;
	}
	
	@Override
    public String toString() {
        return "Escritor{" +
                ", numero=" + numero +
                ", description=" + description +
                '}';
    }
	
}
