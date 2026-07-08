package com.alex.weather_alert_api.controller;

import com.alex.weather_alert_api.dto.response.CurrentWeatherResponse;
import com.alex.weather_alert_api.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather") // Base URL
@RequiredArgsConstructor // final fields for dependency injection
public class WeatherController {

    private final WeatherService weatherService;

    // GET /api/weather/current?location=London
    @GetMapping("/current")
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeather(@RequestParam String location) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(location));
        // Return HTTP 200 OK with CurrentWeatherResponse as response body
    }
}
