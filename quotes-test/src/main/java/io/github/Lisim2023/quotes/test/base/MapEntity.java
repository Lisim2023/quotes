package io.github.Lisim2023.quotes.test.base;

import java.util.Map;

public interface MapEntity {

    String getId();
    void setId(String id);

    Map<String, Object> getDataMap();
    void setDataMap(Map<String, Object> dataMap);

}
