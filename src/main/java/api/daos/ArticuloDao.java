package api.daos;

import java.util.List;

import api.entities.Articulo;

public interface ArticuloDao extends GenericDao<Articulo, String> {
     List<Articulo> findByEscritoresNotEmpty();
}
