package md.pharm.restservice.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Andrei on 9/24/2015.
 */
public class Response {
    private String responseCode;
    private String responseMessage;
    private Map<String, Object> map;

    public Response(){
        map = new HashMap<String, Object>();
    }

    public Response(String responseCode, String responseMessage, Map<String, Object> map) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.map = map;
    }

    public void addMapItem(String name, Object object){
        map.put(name, object);
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (responseCode != null ? !responseCode.equals(response.responseCode) : response.responseCode != null)
            return false;
        if (responseMessage != null ? !responseMessage.equals(response.responseMessage) : response.responseMessage != null)
            return false;
        return !(map != null ? !map.equals(response.map) : response.map != null);

    }

    @Override
    public int hashCode() {
        int result = responseCode != null ? responseCode.hashCode() : 0;
        result = 31 * result + (responseMessage != null ? responseMessage.hashCode() : 0);
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }
}
