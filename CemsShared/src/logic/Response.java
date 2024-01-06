package logic;

import java.io.Serializable;

// This class has two private instance
// variables `responseType` and `responseParam`, and a constructor that initializes these variables. It
// also has two getter methods `getResponseType()` and `getResponseParam()` to access these private
// variables. It used to send information from the server to the client.

public class Response implements Serializable {
    private String responseType;
    private Object responseParam;

    public Response(String responseType, Object responseParam) {
        this.responseType = responseType;
        this.responseParam = responseParam;
    }

    public String getResponseType() {
        return responseType;
    }

    public Object getResponseParam() {
        return responseParam;
    }
}
