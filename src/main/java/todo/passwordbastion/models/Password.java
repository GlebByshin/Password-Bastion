package todo.passwordbastion.models;

import org.springframework.stereotype.Component;

public class Password {
    private String password;
    private String service;
    public Password(String password, String service) {
        this.password = password;
        this.service = service;
    }
    public Password() {}

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
