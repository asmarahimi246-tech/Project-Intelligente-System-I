package games.kasia.app.client.common;

import java.util.HashMap;

/**
 * WHYsonDecoder
 * 
 * a "json" decoder that decodes WHYson (the datatype the school uses because why not use something thats almost json but not exactly json)
 */
public class WHYsonParser {
    private HashMap<String, String> params = new HashMap<String, String>();

    /**
     * The WHYson decoder alows you to get the values of each param
     * 
     * @param whyson the WHYson to decode format of a WHYson:
     * COMMAND {PARAM: "value"}
     */
    public WHYsonParser(String whyson) {
        setup(whyson);
    }

    /**
     * decodes the WHYson
     * 
     * @param whyson
     */
    private void setup (String whyson) {
        whyson = whyson.substring(whyson.indexOf("{") + 1);
        String whysonParamsString = whyson.substring(0, whyson.indexOf("}"));

        String[] whysonParams = whysonParamsString.split(",");

        for (String param : whysonParams) {
            String[] paramTuple = param.split(":");

            String value = paramTuple[1].substring(paramTuple[1].indexOf("\"") + 1);
            String cleanValue = value.substring(0, value.indexOf("\"")).trim();

            params.put(paramTuple[0].trim(), cleanValue);
        }
    }

    /**
     * gets the value of a param
     * 
     * @param param
     * @return
     */
    public String getValue (String param) {
        return params.get(param);
    }
}
