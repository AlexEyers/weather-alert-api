package com.alex.weather_alert_api.client;

import com.alex.weather_alert_api.config.WeatherApiProperties;
import com.alex.weather_alert_api.dto.weatherapi.WeatherApiCurrentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component // To create the object when the app starts as WeatherService will need it
@RequiredArgsConstructor
public class WeatherApiClient {

    // Controller handles the incoming HTTP request THEN
    // This class makes the outgoing HTTP request to WeatherApi.com
    // So this class needs to :
    // 1.) Know the WeatherApi.com base URL
    // 2.) Know the API key
    // 3.) Build the correct URL for a city
    // 4.) Convert the JSON response into our DTOs

    // We already stored the base URL and the key in here
    private final WeatherApiProperties weatherApiProperties;

    public WeatherApiCurrentResponse getCurrentWeatherByLocation(String location) {
        // Create an HTTP client pre-configured with a base URL
        RestClient restClient = RestClient.create(weatherApiProperties.getBaseUrl());

        // .get() tells client we want to make a GET request
        return restClient.get().uri(
                uriBuilder -> uriBuilder // Build URL for this request
                        .path("/current.json") // Adds /current.json
                        .queryParam("key", weatherApiProperties.getKey()) // Adds API key (?key=your_api_key)
                        .queryParam("q", location) // Adds the location (&q=London) or whatever is passed in
                        .build())
                // Final example URL = https://api.weatherapi.com/v1/current.json?key=YOUR_REAL_API_KEY&q=London

                // Most HTTP URLs follow this structure :
                // scheme://host/base-path/resource-path?query-parameters

                // For this request :
                // https:// = scheme/protocol
                // api.weatherapi.com = host/domain
                // /v1 = API version/base path
                // /current.json = resource/endpoint path
                // ?key=YOUR_KEY&q=London = query parameters

                // Query parameters start with "?" after the resource/endpoint path.
                // Additional query parameters are separated with "&".
                // Spring's queryParam(...) handles that formatting and URL encoding for us.
                .retrieve()// Sends the request and retrieves the response
                .body(WeatherApiCurrentResponse.class); // Uses Jackson to convert the JSON response body into our WeatherApiCurrentResponse DTO.
    }
}
