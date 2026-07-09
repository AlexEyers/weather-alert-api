package com.alex.weather_alert_api.dto.response;

public record CurrentWeatherResponse(

    String location,
    String country,
    String localTime,

    double tempC,
    double feelsLikeC,
    int humidity,
    String condition
) {
}
