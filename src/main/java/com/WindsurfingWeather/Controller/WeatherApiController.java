package com.WindsurfingWeather.Controller;

import com.WindsurfingWeather.Dto.CityWeatherDto;
import com.WindsurfingWeather.Service.WeatherApiService;
import com.WindsurfingWeather.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
public class WeatherApiController {

    private final WeatherApiService weatherApiService;

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> getBestCity(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        if (date.isAfter(currentDate.plusDays(6))) {
            return ResponseEntity.badRequest().body("It is not possible to predict the weather more than 6 days in advance.");
        }
        weatherApiService.getWeatherData(date);

        CityWeatherDto bestCity = weatherService.getBestCity();
        return ResponseEntity.ok(bestCity);
    }
}