package com.WindsurfingWeather.Service;

import com.WindsurfingWeather.Dto.CityWeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherApiServiceUnitTest {

    @Autowired
    WeatherApiService serviceUnderTest;

    @MockBean
    RestTemplate mockedRestTemplate;

    @Test
    void shouldGetWeatherData() {
        String sampleResponse = "{" +
                "\"data\": [{" +
                "\"temp\": 25.0," +
                "\"wind_spd\": 12.0," +
                "\"datetime\": \"2023-03-21\"" +
                "}]," +
                "\"lat\": 50.0," +
                "\"lon\": 20.0" +
                "}";
        when(mockedRestTemplate.getForObject(any(String.class), eq(String.class))).thenReturn(sampleResponse);
        LocalDate targetDate = LocalDate.of(2023, 3, 21);

        List<CityWeatherDto> result = serviceUnderTest.getWeatherData(targetDate);

        assertAll("CityWeatherDto list",
                () -> assertFalse(result.isEmpty(), "The list should not be empty"),
                () -> result.forEach(cityWeatherDto -> {
                    assertEquals(targetDate, cityWeatherDto.getDate(), "Dates should match");
                    assertTrue(cityWeatherDto.getTemperature() >= 5 && cityWeatherDto.getTemperature() <= 35, "Temperature should be between 5 and 35");
                    assertTrue(cityWeatherDto.getWindSpeed() >= 5 && cityWeatherDto.getWindSpeed() <= 18, "Wind speed should be between 5 and 18");
                })
        );
    }
}