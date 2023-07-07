package com.github.Lisim2023.quotes.test.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class GsonConfig {

    @Bean
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>(){}.getType(),
                new CustomizedObjectTypeAdapter());
        return gsonBuilder.create();
    }



    class CustomizedObjectTypeAdapter extends TypeAdapter<Object> {

        private final TypeAdapter<Object> delegate = new Gson().getAdapter(Object.class);

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            delegate.write(out, value);
        }

        @Override
        public Object read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    List<Object> list = new ArrayList<Object>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(read(in));
                    }
                    in.endArray();
                    return list;

                case BEGIN_OBJECT:
                    Map<String, Object> map = new LinkedTreeMap<String, Object>();
                    in.beginObject();
                    while (in.hasNext()) {
                        map.put(in.nextName(), read(in));
                    }
                    in.endObject();
                    return map;

                case STRING:
                    return in.nextString();

                case NUMBER:
                    //return in.nextDouble();
                    String n = in.nextString();
                    if (n.indexOf('.') != -1) {
                        return Double.parseDouble(n);
                    }
                    return Long.parseLong(n);

                case BOOLEAN:
                    return in.nextBoolean();

                case NULL:
                    in.nextNull();
                    return null;

                default:
                    throw new IllegalStateException();
            }
        }
    }
}
