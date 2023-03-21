package com.WindsurfingWeather.Model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CityWeather {

    private String cityName;
    private double latitude;
    private double longitude;
    private LocalDate date;
    private double temperature;
    private double windSpeed;
}