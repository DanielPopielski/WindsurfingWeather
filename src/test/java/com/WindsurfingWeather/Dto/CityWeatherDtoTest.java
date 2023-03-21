package com.WindsurfingWeather.Dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CityWeatherDtoTest {

    @Test
    public void testCityWeatherDto() {
        // given
        String cityName = "Lubin";
        LocalDate date = LocalDate.of(2023, 3, 5);
        double temperature = 15.0;
        double windSpeed = 10;
        double latitude = 54.3;
        double longitude = 18.6;

        // when
        CityWeatherDto cityWeatherDto = new CityWeatherDto(cityName, date, temperature, windSpeed, latitude, longitude);

        // then
        Assertions.assertEquals(cityName, cityWeatherDto.getCityName());
        Assertions.assertEquals(date, cityWeatherDto.getDate());
        Assertions.assertEquals(temperature, cityWeatherDto.getTemperature());
        Assertions.assertEquals(windSpeed, cityWeatherDto.getWindSpeed());
        Assertions.assertEquals(latitude, cityWeatherDto.getLatitude());
        Assertions.assertEquals(longitude, cityWeatherDto.getLongitude());
    }
}