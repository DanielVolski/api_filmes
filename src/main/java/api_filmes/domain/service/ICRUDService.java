package api_filmes.domain.service;

import java.util.List;

public interface ICRUDService<Request, Response> {
    List<Response> getAll();
    Response getById(Long id);
    Response create(Request dto);
    Response update(Long id, Request dto);
    void delete(Long id);     
}
