package com.WindsurfingWeather.Controller;

import com.WindsurfingWeather.Dto.CityWeatherDto;
import com.WindsurfingWeather.Service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
public class WeatherApiController {

    private final WeatherApiService weatherApiService;

    @GetMapping("/weather")
    public ResponseEntity<List<CityWeatherDto>> getWeatherData(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<CityWeatherDto> cityDataList = weatherApiService.getWeatherData(date);
        return ResponseEntity.ok(cityDataList);
    }

    @PostMapping("/weather")
    public ResponseEntity<List<CityWeatherDto>> getWeatherData(@RequestBody Map<String, String> requestBody) {
        String dateStr = requestBody.get("date");
        LocalDate date = LocalDate.parse(dateStr);
        List<CityWeatherDto> cityDataList = weatherApiService.getWeatherData(date);
        return ResponseEntity.ok(cityDataList);
    }
}