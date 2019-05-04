package WeatherAPI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class WeatherForecast{

    public static Weather getCurrentWeather(String city, String country) throws HttpClientErrorException {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city.toLowerCase() + "," + country.toLowerCase() +"&units=metric&APPID=42c57f20541b4050308f26f556c468c1";
        System.out.println(resourceUrl);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(resourceUrl , String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                String response = responseEntity.getBody();

                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                JsonArray weatherArray = (JsonArray) jsonObject.get("weather");
                JsonObject weatherEntity = (JsonObject) weatherArray.get(0);

                Weather weather = new Weather();


                String iconUrl = "http://openweathermap.org/img/w/" + weatherEntity.get("icon").getAsString() + ".png";

                JsonObject main = (JsonObject) jsonObject.get("main");
                JsonObject wind = (JsonObject) jsonObject.get("wind");

                weather.setDescription(weatherEntity.get("description").getAsString());
                weather.setHumidity(main.get("humidity").getAsString());
                weather.setPressure(main.get("pressure").getAsString());
                weather.setTemperature(main.get("temp").getAsString());
                weather.setWindDeg(wind.get("deg").getAsString());
                weather.setWindSpeed(wind.get("speed").getAsString());
                weather.setIcon(new Image(iconUrl));
                return weather;
            }
            return null;
    }

    public static List<Weather> get5DayWeatherForecast(String city, String country) throws HttpClientErrorException{
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city.toLowerCase() + "," + country.toLowerCase() +"&units=metric&APPID=42c57f20541b4050308f26f556c468c1";
        System.out.println(resourceUrl);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(resourceUrl , String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                String response = responseEntity.getBody();

                System.out.println(response);

                List<Weather> weatherList = new ArrayList<Weather>();

                JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
                JsonArray list = (JsonArray)jsonObject.get("list");

                for(int i=0;i<5;i++){
                    JsonObject arrayEntity = (JsonObject) list.get(i);
                    JsonObject main = (JsonObject) arrayEntity.get("main");
                    JsonObject wind = (JsonObject) arrayEntity.get("wind");
                    JsonArray weatherArray = (JsonArray) arrayEntity.get("weather");
                    JsonObject weatherEntity = (JsonObject) weatherArray.get(0);
                    String iconUrl = "http://openweathermap.org/img/w/" + weatherEntity.get("icon").getAsString() + ".png";
                    Weather weather = new Weather();
                    weather.setDescription(weatherEntity.get("description").getAsString());
                    weather.setHumidity(main.get("humidity").getAsString());
                    weather.setPressure(main.get("pressure").getAsString());
                    weather.setTemperature(main.get("temp").getAsString());
                    weather.setWindDeg(wind.get("deg").getAsString());
                    weather.setWindSpeed(wind.get("speed").getAsString());
                    weather.setIcon(new Image(iconUrl));
                    weatherList.add(weather);
                }
                return weatherList;
            }
    return null;
    }

}
