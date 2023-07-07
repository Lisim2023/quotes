package io.github.lisim2023.quotes.common;

import io.github.lisim2023.quotes.dict.DictModel;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonUtil {

    public static String toString(Object object){
        return object == null ? "" : object.toString();
    }


    public static Map<String, Map<String, Object>> arrangeDictModels(List<DictModel> dictModelList, @Nullable List<String> dictCodes){
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        if (dictModelList == null || dictModelList.size() == 0){
            return resultMap;
        }
        if (dictCodes == null){
            dictCodes = dictModelList.stream()
                    .map(DictModel::getCode)
                    .distinct()
                    .collect(Collectors.toList());
        }
        for (String code : dictCodes) {
            Map<String, Object> dictMap = new HashMap<>();
            List<DictModel> dictModels = dictModelList.stream()
                    .filter(dictModel -> code.equals(dictModel.getCode()))
                    .collect(Collectors.toList());
            for (DictModel dictModel : dictModels) {
                dictMap.put(dictModel.getValue(), dictModel.getLabel());
            }
            resultMap.put(code, dictMap);
        }
        return resultMap;
    }
}
