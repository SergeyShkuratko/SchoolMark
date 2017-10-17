package jsonconverter;

import com.google.gson.Gson;

public class JsonConverter implements Converter {

    public String convert(Object value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
