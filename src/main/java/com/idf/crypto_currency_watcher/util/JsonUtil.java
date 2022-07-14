package com.idf.crypto_currency_watcher.util;

import com.idf.crypto_currency_watcher.exception.ParsingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    public static Map<String, Double> jsonToMapCryptoCost(String jsonStr){
        Map<String, Double> result = new HashMap<>();
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(jsonStr);
            for (Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj;
                result.put(
                        (String) jsonObject.get("symbol"),
                        Double.parseDouble((String) jsonObject.get("price_usd"))
                );
            }
        } catch (ParseException e) {
            throw new ParsingException(e.getMessage());
        }
        return result;
    }
}
