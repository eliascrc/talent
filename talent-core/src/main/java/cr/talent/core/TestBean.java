package cr.talent.core;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
    private String message;

    public TestBean(){
        this.message = "Talent! rules";
    }

    public TestBean(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
