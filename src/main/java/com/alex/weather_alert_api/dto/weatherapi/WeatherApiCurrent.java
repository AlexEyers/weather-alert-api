package com.alex.weather_alert_api.dto.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiCurrent {

    @JsonProperty("temp_c")
    private double tempC;

    @JsonProperty("feelslike_c")
    private double feelsLikeC;

    private int humidity;
    private WeatherApiCondition condition;
}
