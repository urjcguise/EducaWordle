package app.TFGWordle.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class UserState {

    private String userName;
    private String email;
    private JsonNode state;

    public UserState(String userName, String email, JsonNode state) {
        this.userName = userName;
        this.email = email;
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JsonNode getState() {
        return state;
    }

    public void setState(JsonNode state) {
        this.state = state;
    }
}
