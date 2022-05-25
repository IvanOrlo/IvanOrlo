package forserver;

public class ResponseExample {
    String token;
    int status;

    public String getToken() {
        return token;
    }

    public ResponseExample(String token, int status) {
        this.token = token;
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
