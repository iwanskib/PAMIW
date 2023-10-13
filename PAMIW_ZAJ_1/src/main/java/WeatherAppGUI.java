import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WeatherAppGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JTextField cityField = new JTextField(20);
        JButton startButton = new JButton("Start");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputPanel.add(cityField);
        inputPanel.add(startButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                String response;
                try {
                    String cityKey = WeatherApp.getCityKey(city);
                    if (cityKey != null) {
                        String weatherData = WeatherApp.getCurrentConditions(cityKey);
                        response = "Aktualna pogoda w mieście " + city + ": " + weatherData;

                        String fiveDayForecast = WeatherApp.getFiveDayForecast(cityKey);
                        response += "\nPogoda na kolejne 5 dni: " + fiveDayForecast;

                        String hourly12hForecast = WeatherApp.getHourly12hForecast(cityKey);
                        response += "\nPogoda na następne 12h: " + hourly12hForecast;

                        String dailyForecastNextDay = WeatherApp.getDailyForecastForNextDay(cityKey);
                        response += "\nPogoda na jutro: " + dailyForecastNextDay;
                    } else {
                        response = "Nie ma takiego miasta!";
                    }
                } catch (IOException ioException) {
                    response = "Error: " + ioException.getMessage();
                }
                outputArea.setText(response);
            }
        });
        frame.setVisible(true);
    }
}
