package com.WindsurfingWeather.Service;

import com.WindsurfingWeather.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class WeatherApiService {

    public WeatherApiService() {
    }

    @Autowired
    RestTemplate restTemplate;

    @Value("${apiKey}")
    private String apiKey;

    @Value("${apiBaseUrl}")
    private String apiBaseUrl;

    private final List<String> cities = Arrays.asList("Jastarnia", "Bridgetown", "Forteleza", "Pissouri", "Le Morne");

    public List<CityWeatherDto> getWeatherData(LocalDate date) {
        List<CityWeatherDto> cityWeatherDtoList = cities.stream()
                .map(city -> {
                    String url = String.format("%s?key=%s&units=M&city=%s&date=%s", apiBaseUrl, apiKey, city, date.toString());
                    String jsonResponse = restTemplate.getForObject(url, String.class);
                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    JSONArray data = jsonObject.getJSONArray("data");
                    JSONObject day = findDay(data, date.toString());
                    assert day != null;

                    return new CityWeatherDto(city, date, day.getDouble("temp"), day.getDouble("wind_spd"), jsonObject.getDouble("lat"), jsonObject.getDouble("lon"));
                })
                .filter(cityWeatherDto -> cityWeatherDto.getTemperature() >= -5 && cityWeatherDto.getTemperature() <= 100 && cityWeatherDto.getWindSpeed() >= -10 && cityWeatherDto.getWindSpeed() <= 100)
                .toList();

        return new ArrayList<>(cityWeatherDtoList);
    }

    private static JSONObject findDay(JSONArray data, String date) {
        for (int i = 0; i < data.length(); i++) {
            JSONObject day = data.getJSONObject(i);
            String datetime = day.getString("datetime");
            if (datetime.equals(date)) {
                return day;
            }
        }
        return null;
    }
}