package api.entities;

import java.time.LocalDateTime;

public class Comentario {
	private String id;
	private Boolean negative;
	private String description;
	private LocalDateTime date;	
	public Comentario(Boolean negative,String description,LocalDateTime date) {
		this.negative = negative;
		this.description = description;
		this.date = date;
	}
     
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	public Boolean getNegative() {
		return negative;
	}
	
	public void setNegative(Boolean negative) {
		this.negative = negative;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	@Override
    public String toString() {
        return "Comentario {" +
                "id='" + id + '\'' +
                ", negative=" + negative +
                ", description='" + description + '\'' +
                ", date="+date+'\''+
                '}';
    }
	
}
