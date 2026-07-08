package com.alex.weather_alert_api.dto.weatherapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiCurrentResponse {

    private WeatherApiLocation location;
    private WeatherApiCurrent current;
}
