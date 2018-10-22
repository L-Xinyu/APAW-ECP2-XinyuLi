package api.apiControllers;
import api.dtos.TemaDto;
import api.exceptions.*;

public class TemaApiController {

    public static final String TEMAS = "/temas";

    public static final String ID_ID = "/{id}";

    private TemaApiController temaBusinessController = new TemaApiController();

    public String create(TemaDto temaDto) {
        this.validate(temaDto, "temaDto");
        this.validate(temaDto.getName(), "TemaDto Name");
        return this.temaBusinessController.create(temaDto);
    }

    public void update(String id, TemaDto temaDto) {
        this.validate(temaDto, "temaDto");
        this.validate(temaDto.getName(), "TemaDto Name");
        this.temaBusinessController.update(id, temaDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is missing");
        }
    }

}
