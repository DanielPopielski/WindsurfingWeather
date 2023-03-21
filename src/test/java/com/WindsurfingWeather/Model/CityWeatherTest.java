package com.WindsurfingWeather.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CityWeatherTest {

    @Test
    public void testCityWeather() {
        // given
        String cityName = "Lubin";
        double latitude = 54.3;
        double longitude = 18.6;
        LocalDate date = LocalDate.of(2023, 2, 15);
        double temperature = 15.6;
        double windSpeed = 10;

        // when
        CityWeather cityWeather = new CityWeather();
        cityWeather.setCityName(cityName);
        cityWeather.setLatitude(latitude);
        cityWeather.setLongitude(longitude);
        cityWeather.setDate(date);
        cityWeather.setTemperature(temperature);
        cityWeather.setWindSpeed(windSpeed);

        // then
        Assertions.assertEquals(cityName, cityWeather.getCityName());
        Assertions.assertEquals(latitude, cityWeather.getLatitude());
        Assertions.assertEquals(longitude, cityWeather.getLongitude());
        Assertions.assertEquals(date, cityWeather.getDate());
        Assertions.assertEquals(temperature, cityWeather.getTemperature());
        Assertions.assertEquals(windSpeed, cityWeather.getWindSpeed());
    }
}