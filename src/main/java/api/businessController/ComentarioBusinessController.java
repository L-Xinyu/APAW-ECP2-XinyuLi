package api.businessController;

import api.daos.DaoFactory;
import api.dtos.ComentarioDto;
import api.entities.Comentario;

public class ComentarioBusinessController {
 
	public void create(ComentarioDto comentarioDto) {
		Comentario comentario = new Comentario(comentarioDto.getNegative(), comentarioDto.getDescription(),comentarioDto.getDate());
	    DaoFactory.getFactory().getComentarioDao().save(comentario);
	}
	
}
