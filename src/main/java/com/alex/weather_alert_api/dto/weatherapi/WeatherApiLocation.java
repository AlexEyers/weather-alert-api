package com.alex.weather_alert_api.dto.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiLocation {

    private String name; // Maps location.name from WeatherAPI.com's JSON
    private String country; // Maps location.country
    @JsonProperty("localtime") // Converts the JSON name localtime -> localTime for Java
    private String localTime; // Maps location.localtime
}
