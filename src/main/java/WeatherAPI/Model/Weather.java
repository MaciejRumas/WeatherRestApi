package WeatherAPI.Model;

import javafx.scene.image.Image;

public class Weather {
    private String description;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;
    private String windDeg;
    private Image icon;

    @Override
    public String toString() {
        return description + "\n" + temperature + " \u00b0C \n" + pressure + " hPa \n" + humidity + " % \n" + windSpeed + " m/s, " + windDeg + " \u00b0";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String wind) {
        this.windSpeed = wind;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

}
