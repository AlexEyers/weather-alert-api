package com.alex.weather_alert_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter // Spring auto maps base-url to baseUrl (and obv key to key)
@Component // Create class as a Spring-managed object, so other classes can ask Spring for it later
@ConfigurationProperties(prefix = "weather.api") // Look inside application.yaml under weather.api and bind those values into this class
public class WeatherApiProperties {

    // Reads weather.api.base-url from application.yaml
    // Value comes from base-url : https://api.weatherapi.com/v1
    private String baseUrl;
    // Reads weather.api.key from application.yaml
    // Value comes from ${WEATHER_API_KEY} - which comes from .env file
    private String key;
}
