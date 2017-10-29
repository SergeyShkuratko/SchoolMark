package controller.jsonconverter;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter implements Converter {

    public String convert(Object value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
