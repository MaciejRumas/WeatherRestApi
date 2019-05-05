package WeatherAPI;

import org.springframework.web.client.HttpClientErrorException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Controller implements Initializable {

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField cityTextField1;

    @FXML
    private TextField countryTextField1;

    @FXML
    private ImageView weatherIconImageView;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label cityLabel1;

    @FXML
    private Label countryLabel1;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label descLabel1;
    @FXML
    private Label descLabel2;
    @FXML
    private Label descLabel3;
    @FXML
    private Label descLabel4;
    @FXML
    private Label descLabel5;

    @FXML
    private ImageView iconImageView1;
    @FXML
    private ImageView iconImageView2;
    @FXML
    private ImageView iconImageView3;
    @FXML
    private ImageView iconImageView4;
    @FXML
    private ImageView iconImageView5;

    @FXML
    private Label infoLabel;
    @FXML
    private Label infoLabel1;
    @FXML
    private Label infoLabel2;
    @FXML
    private Label infoLabel3;
    @FXML
    private Label infoLabel4;
    @FXML
    private Label infoLabel5;

    public void initialize(URL location, ResourceBundle resources) {
        String info = "Description: \nTemperature: \nPressure: \nHumidity: \nWind:";
        infoLabel.setText(info);
        infoLabel1.setText(info);
        infoLabel2.setText(info);
        infoLabel3.setText(info);
        infoLabel4.setText(info);
        infoLabel5.setText(info);
    }

    public void getWeatherButtonClick(ActionEvent event) {

        if (textFieldsOk(cityTextField, countryTextField, cityLabel, countryLabel)) {
            try {
               Weather currentWeather = WeatherForecast.getCurrentWeather(cityTextField.getText(), countryTextField.getText());
               descriptionLabel.setText(currentWeather.toString());
               weatherIconImageView.setImage(currentWeather.getIcon());

            } catch (HttpClientErrorException e) {
                ApiAlert apiAlert = new ApiAlert(e);
                apiAlert.show();
            }
        }
    }

    public void getForecastButtonClick(ActionEvent event) {

        if (textFieldsOk(cityTextField1, countryTextField1, cityLabel1, countryLabel1)) {
            try {
                List<Weather> weatherList = WeatherForecast.get5DayWeatherForecast(cityTextField1.getText(), countryTextField1.getText());

                descLabel1.setText(weatherList.get(0).toString());
                descLabel2.setText(weatherList.get(1).toString());
                descLabel3.setText(weatherList.get(2).toString());
                descLabel4.setText(weatherList.get(3).toString());
                descLabel5.setText(weatherList.get(4).toString());

                iconImageView1.setImage(weatherList.get(0).getIcon());
                iconImageView2.setImage(weatherList.get(1).getIcon());
                iconImageView3.setImage(weatherList.get(2).getIcon());
                iconImageView4.setImage(weatherList.get(3).getIcon());
                iconImageView5.setImage(weatherList.get(4).getIcon());

            } catch (HttpClientErrorException e) {
                ApiAlert apiAlert = new ApiAlert(e);
                apiAlert.show();
            }
        }
    }

    private static boolean textFieldsOk(TextField city, TextField country, Label cityLabel, Label countryLabel){
        cityLabel.setTextFill(Color.web("#db0000"));
        countryLabel.setTextFill(Color.web("#db0000"));
        if (city.getText().equals("") && country.getText().equals("")) {
            cityLabel.setText("Fill in city field");
            countryLabel.setText("Fill in country field");
            return false;
        } else if (city.getText().equals("") && !country.getText().equals("")) {
            cityLabel.setText("Fill in city field");
            return false;
        } else if (!city.getText().equals("") && country.getText().equals("")) {
            countryLabel.setText("Fill in country field");
            return false;
        }

        cityLabel.setText("");
        countryLabel.setText("");
        return true;
    }
}
