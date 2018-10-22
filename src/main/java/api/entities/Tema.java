package api.entities;

public class Tema {
	
	private String id;
	
	private String name;
	
	public Tema(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
        return "Tema{" +
                "id='" + id + '\'' +
                ", nick='" + name + '\'' +
                '}';
    }
	
}
