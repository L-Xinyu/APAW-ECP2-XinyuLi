package api.apiControllers;

import java.util.List;
import api.businessController.ArticuloBusinessController;
import api.dtos.ArticuloDto;
import api.dtos.ArticuloIdNameDto;
import api.entities.Category;
import api.exceptions.*;


public class ArticuloApiController {
    public static final String Articulos = "/articulos";

    public static final String Escritores = "/escritores";

    public static final String AVERAGE = "/average";

    public static final String CATEGORY = "/category";

    private ArticuloBusinessController articuloBusinessController = new ArticuloBusinessController();

    public String create(ArticuloDto articuloDto) {
        this.validate(articuloDto, "articuloDto");
        this.validate(articuloDto.getName(), "articuloDto name");
        return this.articuloBusinessController.create(articuloDto);
    }

    public List<ArticuloIdNameDto> readAll() {
        return this.articuloBusinessController.readAll();
    }

    public void createVote(String articuloId, Integer escritor) {
        this.validate(escritor, "escritor");
        if (escritor < 0 || escritor > 10) {
            throw new ArgumentNotValidException("escritor is between 0 and 10");
        }
        this.articuloBusinessController.createVote(articuloId, escritor);
    }

    public void updateCategory(String articuloId, Category category) {
        this.validate(category, "category");
        this.articuloBusinessController.updateCategory(articuloId, category);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

    public List<ArticuloIdNameDto> find(String query) {
        this.validate(query, "query param q");
        if (!"average".equals(query.split(":>=")[0])) {
            throw new ArgumentNotValidException("query param q is incorrect, missing 'average:>='");
        }
        return this.articuloBusinessController.findByAverageGreaterThanEqual(Double.valueOf(query.split(":>=")[1]));
    }

    public void updateCategory(String articuloId, java.util.Locale.Category category) {
        // TODO Auto-generated method stub

    }
}
