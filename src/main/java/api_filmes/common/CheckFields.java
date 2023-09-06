package api_filmes.common;

import api_filmes.domain.exception.BadRequestException;

public class CheckFields {

    public void verifyFields(String email, String password, String name) {
        if (email == null || password == null || name == null) {
            throw new BadRequestException("Preencha todos os campos!");
        }
    }
    
}
