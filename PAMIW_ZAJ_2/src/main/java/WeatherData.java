import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class WeatherData {
    private String currentConditions;
    private String fiveDayForecast;
    private String hourly12hForecast;
    private String dailyForecastNextDay;

    private List<ChangeListener> listeners = new ArrayList<>();

    public String getCurrentConditions() {
        return currentConditions;
    }

    public void setCurrentConditions(String currentConditions) {
        this.currentConditions = currentConditions;
        notifyListeners();
    }

    public String getFiveDayForecast() {
        return fiveDayForecast;
    }

    public void setFiveDayForecast(String fiveDayForecast) {
        this.fiveDayForecast = fiveDayForecast;
        notifyListeners();
    }

    public String getHourly12hForecast() {
        return hourly12hForecast;
    }

    public void setHourly12hForecast(String hourly12hForecast) {
        this.hourly12hForecast = hourly12hForecast;
        notifyListeners();
    }

    public String getDailyForecastNextDay() {
        return dailyForecastNextDay;
    }

    public void setDailyForecastNextDay(String dailyForecastNextDay) {
        this.dailyForecastNextDay = dailyForecastNextDay;
        notifyListeners();
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }
}
