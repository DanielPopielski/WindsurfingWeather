package com.WindsurfingWeather.Controller;

import com.WindsurfingWeather.Dto.CityWeatherDto;
import com.WindsurfingWeather.Service.WeatherApiService;
import com.WindsurfingWeather.Service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WeatherApiController.class)
public class WeatherApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherApiService weatherApiService;

    @MockBean
    private WeatherService weatherService;

    private CityWeatherDto cityWeatherDto;

    @BeforeEach
    void setUp() {
        cityWeatherDto = new CityWeatherDto("BestCity", LocalDate.of(2023, 3, 21), 30, 15, 5, 3);
    }
    @Test
    void getBestCity() throws Exception {
        LocalDate validDate = LocalDate.now().plusDays(2);

        when(weatherApiService.getWeatherData(any(LocalDate.class))).thenReturn(Collections.emptyList());
        when(weatherService.getBestCity()).thenReturn(cityWeatherDto);

        mockMvc.perform(get("/weather")
                        .param("date", validDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"cityName\":\"BestCity\",\"date\":\"2023-03-21\",\"temperature\":30,\"windSpeed\":15,\"latitude\":5,\"longitude\":3}"));
    }

    @Test
    void getBestCityBadRequest() throws Exception {
        LocalDate invalidDate = LocalDate.now().plusDays(7);

        mockMvc.perform(get("/weather")
                        .param("date", invalidDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("It is not possible to predict the weather more than 6 days in advance."));
    }
}