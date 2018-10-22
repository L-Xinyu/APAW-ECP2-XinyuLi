package api;

import api.apiControllers.ArticuloApiController;
import api.apiControllers.ComentarioApiController;
import api.apiControllers.TemaApiController;
import api.dtos.ArticuloDto;
import api.dtos.ComentarioDto;
import api.dtos.TemaDto;
import api.entities.Category;
import api.exceptions.*;

import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;


public class Dispatcher {
//	static {
//        DaoFactory.setFactory(new DaoMemoryFactory());
//    }

    private TemaApiController temaApiController = new TemaApiController();

    private ComentarioApiController comentarioApiController = new ComentarioApiController();

    private ArticuloApiController articuloApiController = new ArticuloApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                case DELETE:
                    this.doDelete(request);
                    break;
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (RequestInvalidException exception) {
                response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
                response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(TemaApiController.TEMAS)) {
            response.setBody(this.temaApiController.create((TemaDto) request.getBody()));
        } else if (request.isEqualsPath(ComentarioApiController.Comentarios)) {
            this.comentarioApiController.create((ComentarioDto) request.getBody());
        } else if (request.isEqualsPath(ArticuloApiController.Articulos)) {
            response.setBody(this.articuloApiController.create((ArticuloDto) request.getBody()));
        } else if (request.isEqualsPath(ArticuloApiController.Articulos + ArticuloApiController.ID_ID + ArticuloApiController.Escritores)) {
            this.articuloApiController.createVote(request.getPath(1), (Integer) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(ArticuloApiController.Articulos)) {
            response.setBody(this.articuloApiController.readAll());
        } else if (request.isEqualsPath(ArticuloApiController.Articulos + ArticuloApiController.ID_ID + ArticuloApiController.AVERAGE)) {
            response.setBody(this.articuloApiController.readAverage(request.getPath(1)));
        } else if (request.isEqualsPath(ArticuloApiController.Articulos + ArticuloApiController.SEARCH)) {
            response.setBody(this.articuloApiController.find(request.getParams().get("q")));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(TemaApiController.TEMAS + TemaApiController.ID_ID)) {
            this.temaApiController.update(request.getPath(1), (TemaDto) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request) {
        if (request.isEqualsPath(ArticuloApiController.Articulos + ArticuloApiController.ID_ID + ArticuloApiController.CATEGORY)) {
            this.articuloApiController.updateCategory(request.getPath(1), (Category) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doDelete(HttpRequest request) {
        if (request.isEqualsPath(ArticuloApiController.Articulos + ArticuloApiController.ID_ID)) {
            this.articuloApiController.delete(request.getPath(1));
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }
	
}
