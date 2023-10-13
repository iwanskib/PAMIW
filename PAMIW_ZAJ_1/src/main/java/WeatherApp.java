import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
public class WeatherApp {
    private static final String API_KEY = "HAg9tfuwo26iz0iNg2II0crFELOxUreE";
    private static final String AUTOCOMPLETE_ENDPOINT = "https://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=" + API_KEY + "&q=%s&language=pl";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "https://dataservice.accuweather.com/currentconditions/v1/%s?apikey=" + API_KEY + "&language=pl";
    private static final String FIVE_DAY_FORECAST_ENDPOINT = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/%s?apikey=" + API_KEY + "&language=pl";
    private static final String HOURLY_12H_FORECAST_ENDPOINT = "https://dataservice.accuweather.com/forecasts/v1/hourly/12hour/%s?apikey=" + API_KEY + "&language=pl";
    private static final String DAILY_FORECAST_1DAY_ENDPOINT = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/%s?apikey=" + API_KEY + "&language=pl";
    public static String getCityKey(String city) throws IOException {
        String endpoint = String.format(AUTOCOMPLETE_ENDPOINT, city);
        String jsonResponse = makeApiRequest(endpoint);

        JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (jsonArray.size() > 0) {
            JsonObject cityObject = jsonArray.get(0).getAsJsonObject();
            return cityObject.get("Key").getAsString();
        }

        return null;
    }
    public static String getCurrentConditions(String cityKey) throws IOException {
        String endpoint = String.format(CURRENT_CONDITIONS_ENDPOINT, cityKey);
        String jsonResponse = makeApiRequest(endpoint);

        JsonArray jsonArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
        if (jsonArray.size() > 0) {
            JsonObject currentConditionsObject = jsonArray.get(0).getAsJsonObject();
            return currentConditionsObject.get("WeatherText").getAsString();
        }
        return "Brak danych o pogodzie";
    }
    public static String getFiveDayForecast(String cityKey) throws IOException {
        String endpoint = String.format(FIVE_DAY_FORECAST_ENDPOINT, cityKey);
        String jsonResponse = makeApiRequest(endpoint);

        JsonObject forecastObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return forecastObject.get("Headline").getAsJsonObject().get("Text").getAsString();
    }
    public static String getHourly12hForecast(String cityKey) throws IOException {
        String endpoint = String.format(HOURLY_12H_FORECAST_ENDPOINT, cityKey);
        String jsonResponse = makeApiRequest(endpoint);

        JsonArray forecastArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
        JsonObject firstHourForecast = forecastArray.get(0).getAsJsonObject();
        return firstHourForecast.get("IconPhrase").getAsString();
    }
    public static String getDailyForecastForNextDay(String cityKey) throws IOException {
        String endpoint = String.format(DAILY_FORECAST_1DAY_ENDPOINT, cityKey);
        String jsonResponse = makeApiRequest(endpoint);

        JsonObject forecastObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject dailyForecast = forecastObject.getAsJsonArray("DailyForecasts").get(0).getAsJsonObject();
        double minTemp = ((dailyForecast.getAsJsonObject("Temperature").getAsJsonObject("Minimum").get("Value").getAsInt()-32)/1.8);
        double maxTemp = (dailyForecast.getAsJsonObject("Temperature").getAsJsonObject("Maximum").get("Value").getAsInt()-32)/1.8;
        String dayWeatherText = dailyForecast.getAsJsonObject("Day").get("IconPhrase").getAsString();
        String nightWeatherText = dailyForecast.getAsJsonObject("Night").get("IconPhrase").getAsString();

        return String.format("\nMin Temp: %.2s C\nMax Temp: %.2s C\nDzień: %s\nNoc: %s", minTemp, maxTemp, dayWeatherText, nightWeatherText);
    }
    public static String makeApiRequest(String endpoint) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(endpoint);

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity);
        }
        return "{}";
    }
}

