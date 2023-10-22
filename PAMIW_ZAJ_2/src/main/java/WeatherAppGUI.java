import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WeatherAppGUI {

    private JTextArea outputArea;
    private JTextField cityField;
    private final WeatherViewModel viewModel;
    private final WeatherData weatherData;

    public WeatherAppGUI(WeatherViewModel viewModel) {
        this.viewModel = viewModel;
        this.weatherData = viewModel.getWeatherData();
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        cityField = new JTextField(20);
        JButton startButton = new JButton("Start");

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputPanel.add(cityField);
        inputPanel.add(startButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        weatherData.addChangeListener(e -> {
            outputArea.setText(buildWeatherOutput());
        });

        startButton.addActionListener(e -> {
            String city = cityField.getText();
            viewModel.fetchWeatherForCity(city);
        });

        frame.setVisible(true);
    }

    private String buildWeatherOutput() {
        return "Aktualna pogoda: " + weatherData.getCurrentConditions() +
                "\nPogoda na kolejne 5 dni: " + weatherData.getFiveDayForecast() +
                "\nPogoda na następne 12h: " + weatherData.getHourly12hForecast() +
                "\nPogoda na jutro: " + weatherData.getDailyForecastNextDay();
    }

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        WeatherApp weatherApp = new WeatherApp();
        WeatherViewModel viewModel = new WeatherViewModel(weatherData, weatherApp);
        new WeatherAppGUI(viewModel);
    }
}
