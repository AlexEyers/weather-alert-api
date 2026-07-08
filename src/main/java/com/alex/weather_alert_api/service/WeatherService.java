package com.alex.weather_alert_api.service;

import com.alex.weather_alert_api.client.WeatherApiClient;
import com.alex.weather_alert_api.dto.response.CurrentWeatherResponse;
import com.alex.weather_alert_api.dto.weatherapi.WeatherApiCurrentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Same as @Component but with clearer intent for the dev
@RequiredArgsConstructor // Inject WeatherApiClient
public class WeatherService {

    private final WeatherApiClient weatherApiClient;

    public CurrentWeatherResponse getCurrentWeather(String location) {
        WeatherApiCurrentResponse weatherApiCurrentResponse =
                weatherApiClient.getCurrentWeatherByLocation(location);

        return new CurrentWeatherResponse(
                weatherApiCurrentResponse.getLocation().getName(),
                weatherApiCurrentResponse.getLocation().getCountry(),
                weatherApiCurrentResponse.getLocation().getLocalTime(),
                weatherApiCurrentResponse.getCurrent().getTempC(),
                weatherApiCurrentResponse.getCurrent().getFeelsLikeC(),
                weatherApiCurrentResponse.getCurrent().getHumidity(),
                weatherApiCurrentResponse.getCurrent().getCondition().getText()
        );
    }
}
