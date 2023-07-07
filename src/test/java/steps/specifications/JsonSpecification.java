package steps.specifications;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonSpecification {
    public static Gson setGsonBuilder() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
}
