package com.alex.weather_alert_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrentWeatherResponse {

    private String location;
    private String country;
    private String localTime;

    private double tempC;
    private double feelsLikeC;
    private int humidity;
    private String condition;
}
