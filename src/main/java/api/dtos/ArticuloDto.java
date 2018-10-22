package api.dtos;

import api.entities.Category;

public class ArticuloDto {
	
	private String name;

	private Category category;

    private String userId;

    public ArticuloDto(String name, Category category, String userId) {
        this.name = name;
        this.category = category;
        this.userId = userId;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ArticuloDto{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", userId='" + userId + '\'' +
                '}';
    }

}
