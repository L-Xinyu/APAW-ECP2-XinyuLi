package api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Articulo {
	
    private String id;
	private String name;
    private LocalDateTime date;
    private Category category; 
    private Tema tema;
    private List<Escritor> escritores;
    
    public Articulo(String name) {
        this.name = name;
        this.date = LocalDateTime.now();
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<Escritor> getEscritores() {
		return escritores;
	}

	public void setEscritores(List<Escritor> escritores) {
		this.escritores = escritores;
	}
	
	@Override
    public String toString() {
        return "Articulo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", category=" + category +
                ", tema=" + tema +
                ", escritores=" + escritores +
                '}';
    }
	
	public static Builder builder(String name) {
		return new Builder(name);
	}
	
	public static class Builder {
		private Articulo articulo;
		
		private Builder(String name) {
             this.articulo = new Articulo(name);
		}
        public Builder id(String id) {
            this.articulo.id = id;
            return this;
        }

        public Builder category(Category category) {
            this.articulo.category = category;
            return this;
        }

        public Builder tema(Tema tema) {
            this.articulo.tema = tema;
            return this;
        }

        public Builder escritores(Escritor escritores) {
            this.articulo.escritores.add(escritores);
            return this;
        }

        public Articulo build() {
            return this.articulo;
        }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
    
    
}
