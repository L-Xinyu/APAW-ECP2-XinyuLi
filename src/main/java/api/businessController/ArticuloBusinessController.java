package api.businessController;

import java.util.List;
import java.util.stream.Collectors;

import api.daos.DaoFactory;
import api.dtos.ArticuloDto;
import api.dtos.ArticuloIdNameDto;
import api.entities.Articulo;
import api.entities.Category;
import api.entities.Escritor;
import api.entities.Tema;
import api.exceptions.*;

public class ArticuloBusinessController {

    public String create(ArticuloDto articuloDto) {
        Tema tema = null;
        if (articuloDto.getUserId() != null) {
            tema = DaoFactory.getFactory().getTemaDao().read(articuloDto.getUserId())
                    .orElseThrow(() -> new NotFoundException("Tema (" + articuloDto.getUserId() + ")"));
        }
        Articulo articulo = Articulo.builder(articuloDto.getName()).tema(tema).category(articuloDto.getCategory()).build();
        DaoFactory.getFactory().getArticuloDao().save(articulo);
        return articulo.getId();
    }

    public List<ArticuloIdNameDto> readAll() {
        return DaoFactory.getFactory().getArticuloDao().findAll()
                .stream().map(ArticuloIdNameDto::new)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        DaoFactory.getFactory().getArticuloDao().deleteById(id);
    }

    public void createVote(String articuloId, Integer escritor) {
        Articulo articulo = DaoFactory.getFactory().getArticuloDao().read(articuloId)
                .orElseThrow(() -> new NotFoundException("Articulo (" + articuloId + ")"));
        articulo.getEscritores().add(new Escritor(escritor, articuloId));
        DaoFactory.getFactory().getArticuloDao().save(articulo);
    }

    public Double readAverage(String articuloId) {
        Articulo articulo = DaoFactory.getFactory().getArticuloDao().read(articuloId)
                .orElseThrow(() -> new NotFoundException("Articulo (" + articuloId + ")"));
        return this.average(articulo);
    }

    private Double average(Articulo articulo) {
        return articulo.getEscritores()
                .stream().mapToDouble(Escritor::getNumero).average()
                .orElse(Double.NaN);
    }

    public void updateCategory(String articuloId, Category category) {
        Articulo articulo = DaoFactory.getFactory().getArticuloDao().read(articuloId)
                .orElseThrow(() -> new NotFoundException("Articulo (" + articuloId + ")"));
        articulo.setCategory(category);
        DaoFactory.getFactory().getArticuloDao().save(articulo);
    }

    public List<ArticuloIdNameDto> findByAverageGreaterThanEqual(Double value) {
        return DaoFactory.getFactory().getArticuloDao().findByEscritoresNotEmpty().stream()
                .filter(articulo -> this.average(articulo) >= value)
                .map(ArticuloIdNameDto::new)
                .collect(Collectors.toList());

    }
}

