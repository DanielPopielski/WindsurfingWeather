package com.WindsurfingWeather.Service;

import com.WindsurfingWeather.Dto.CityWeatherDto;
import com.WindsurfingWeather.Service.WeatherService;
import com.WindsurfingWeather.Service.WeatherApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    @Mock
    private WeatherApiService weatherApiService;

    private WeatherService weatherService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService();
        weatherService.setWeatherApiService(weatherApiService);
    }

    @Test
    public void testGetBestCity() {
        List<CityWeatherDto> cityWeatherDtoList = new ArrayList<>();
        CityWeatherDto city1 = new CityWeatherDto("City 1", LocalDate.of(2023, 3, 21), 10.0, 5, 24, 45);
        CityWeatherDto city2 = new CityWeatherDto("City 2", LocalDate.of(2023, 3, 21), 15.0, 3, 24, 45);
        CityWeatherDto city3 = new CityWeatherDto("City 3", LocalDate.of(2023, 3, 21), 20.0, 1, 24, 45);
        cityWeatherDtoList.add(city1);
        cityWeatherDtoList.add(city2);
        cityWeatherDtoList.add(city3);

        when(weatherApiService.getCityWeatherDtoList()).thenReturn(cityWeatherDtoList);

        CityWeatherDto bestCity = weatherService.getBestCity();

        assertEquals(city1.getCityName(), bestCity.getCityName());
    }
}