package api_filmes.domain.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String mensagem) {
        super(mensagem);
    }
    
}