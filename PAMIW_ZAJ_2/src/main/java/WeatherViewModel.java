import java.io.IOException;

public class WeatherViewModel {

    private WeatherData weatherData;
    private WeatherApp weatherApp;

    public WeatherViewModel(WeatherData weatherData, WeatherApp weatherApp) {
        this.weatherData = weatherData;
        this.weatherApp = weatherApp;
    }

    public void fetchWeatherForCity(String city) {
        try {
            String cityKey = weatherApp.getCityKey(city);
            if (cityKey != null) {
                weatherData.setCurrentConditions(weatherApp.getCurrentConditions(cityKey));
                weatherData.setFiveDayForecast(weatherApp.getFiveDayForecast(cityKey));
                weatherData.setHourly12hForecast(weatherApp.getHourly12hForecast(cityKey));
                weatherData.setDailyForecastNextDay(weatherApp.getDailyForecastForNextDay(cityKey));
            }
        } catch (IOException ioException) {
        }
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }
}

