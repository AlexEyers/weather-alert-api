package com.alex.weather_alert_api.controller;

import com.alex.weather_alert_api.dto.response.CurrentWeatherResponse;
import com.alex.weather_alert_api.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather") // Base URL
@RequiredArgsConstructor // final fields for dependency injection
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    // GET /api/weather/current?location=London
    @GetMapping("/current")
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeather(@RequestParam @NotBlank(message = "location is required") @Size(max = 100, message = "location must be 100 characters or fewer") String location) {
        // Return HTTP 200 OK with CurrentWeatherResponse as response body
        return ResponseEntity.ok(weatherService.getCurrentWeather(location));
    }
}