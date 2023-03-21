package com.WindsurfingWeather.Dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CityWeatherDto {

    private String cityName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private double temperature;

    private double windSpeed;

    private double latitude;

    private double longitude;

    @JsonCreator
    public CityWeatherDto(@JsonProperty("cityName") String cityName,
                          @JsonProperty("date") LocalDate date,
                          @JsonProperty("temperature") double temperature,
                          @JsonProperty("windSpeed") double windSpeed,
                          @JsonProperty("latitude") double latitude,
                          @JsonProperty("longitude") double longitude) {
        this.cityName = cityName;
        this.date = date;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}