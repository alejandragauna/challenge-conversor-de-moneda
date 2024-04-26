package api;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public static ApiResponse fromJson(String json) {
        Gson gson = new Gson();
        ApiResponse response = gson.fromJson(json, ApiResponse.class);
        if (response == null) {
            response = new ApiResponse();
        }
        if (response.getConversionRates() == null) {
            response.setConversionRates(new HashMap<>());
        }
        return response;
    }

}



