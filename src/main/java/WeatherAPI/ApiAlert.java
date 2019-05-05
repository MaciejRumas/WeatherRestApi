package WeatherAPI;

import org.springframework.web.client.HttpClientErrorException;

import javafx.scene.control.Alert;

public class ApiAlert extends Alert {

    public ApiAlert(HttpClientErrorException e) {
        super(AlertType.ERROR);
        setContentText(e.getStatusText());
        setHeaderText(e.getLocalizedMessage());
        setTitle("Error");
    }

}
