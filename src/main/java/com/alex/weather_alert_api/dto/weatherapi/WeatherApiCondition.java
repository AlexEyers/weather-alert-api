package com.alex.weather_alert_api.dto.weatherapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiCondition {

    // Maps current.condition.text from WeatherAPI.com's JSON
    private String text;
}
