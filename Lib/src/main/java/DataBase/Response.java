package DataBase;

import java.io.Serializable;

public class Response implements Serializable {
    private String body;
    private boolean isRegistered = false;
    private boolean isAuthorized = false;

    private static final long serialVersionUID = -2893392192176351154L;

    public Response(){
    }
    public String getBody(){return body;}
    public void setBody(String body) {
        this.body = body;
    }
    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    @Override
    public String toString() {
        return "DataBase.Response{" +
                "body='" + body + '\'' +
                ", isRegistered=" + isRegistered +
                ", isAuthorized=" + isAuthorized +
                '}';
    }
}

