package api_filmes.domain.entities;

public class ResponseError {
    private Integer status;
    private String title;
    private String message;

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ResponseError(Integer status, String title, String message) {
        this.status = status;
        this.title = title;
        this.message = message;
    }
    
}
