package api.businessController;

import api.daos.DaoFactory;
import api.dtos.TemaDto;
import api.entities.Tema;
import api.exceptions.NotFoundException;

public class TemaBusinessController {
	
    public String create(TemaDto temaDto) {
        Tema tema = new Tema(temaDto.getName());
        DaoFactory.getFactory().getTemaDao().save(tema);
        return tema.getId();
    }

    public void update(String id, TemaDto temaDto) {
        Tema tema = DaoFactory.getFactory().getTemaDao().read(id)
                .orElseThrow(() -> new NotFoundException("Tema id: " + id));
        tema.setName(temaDto.getName());
        DaoFactory.getFactory().getTemaDao().save(tema);
    }
}
