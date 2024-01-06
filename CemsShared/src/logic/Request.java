package logic;

import java.io.Serializable;

//Request class that holds the String requestType
//and the object that can be anything that you want to pass from server to Client or vice versa.
public class Request implements Serializable {
  private String requestType;
  private Object requestParam;

  public Request(String requestType, Object requestParam) {
    this.requestType = requestType;
    this.requestParam = requestParam;
  }

  public String getRequestType() {
    return requestType;
  }

  public Object getRequestParam() {
    return requestParam;
  }

}
