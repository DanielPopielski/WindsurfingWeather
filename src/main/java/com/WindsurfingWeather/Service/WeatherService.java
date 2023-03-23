package com.WindsurfingWeather.Service;

import com.WindsurfingWeather.Dto.CityWeatherDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
@Data
public class WeatherService {

    @Autowired
    private WeatherApiService weatherApiService;

    public CityWeatherDto getBestCity() {
        return weatherApiService.getCityWeatherDtoList().stream()
                .max(Comparator.comparing(cityWeatherDto -> cityWeatherDto.getWindSpeed() * 3 + cityWeatherDto.getTemperature()))
                .orElse(null);
    }
}
