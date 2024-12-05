package app.TFGWordle.dto;

import com.fasterxml.jackson.databind.JsonNode;

public class UserState {

    private String userName;
    private JsonNode state;

    public UserState(String userName, JsonNode state) {
        this.userName = userName;
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public JsonNode getState() {
        return state;
    }

    public void setState(JsonNode state) {
        this.state = state;
    }
}
