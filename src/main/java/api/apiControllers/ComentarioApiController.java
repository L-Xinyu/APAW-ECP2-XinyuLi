package api.apiControllers;

import api.businessController.ComentarioBusinessController;
import api.dtos.ComentarioDto;
import api.exceptions.ArgumentNotValidException;

public class ComentarioApiController {
    public static final String Comentarios = "/comentarios";

    private ComentarioBusinessController comentarioBusinessController = new ComentarioBusinessController();

    public void create(ComentarioDto comentarioDto) {
        this.validate(comentarioDto, "comentarioDto");
        this.validate(comentarioDto.getNegative(), "ComentarioDto negative");
        this.validate(comentarioDto.getDescription(), "ComentarioDto description");
        this.comentarioBusinessController.create(comentarioDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

}
