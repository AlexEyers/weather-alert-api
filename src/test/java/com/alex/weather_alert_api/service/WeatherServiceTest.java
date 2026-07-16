package com.alex.weather_alert_api.service;

import com.alex.weather_alert_api.client.WeatherApiClient;
import com.alex.weather_alert_api.dto.response.CurrentWeatherResponse;
import com.alex.weather_alert_api.dto.weatherapi.WeatherApiCondition;
import com.alex.weather_alert_api.dto.weatherapi.WeatherApiCurrent;
import com.alex.weather_alert_api.dto.weatherapi.WeatherApiCurrentResponse;
import com.alex.weather_alert_api.dto.weatherapi.WeatherApiLocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.ResourceAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

// Testing if WeatherApiClient returns fake WeatherAPI data,
// does WeatherService correctly convert it into CurrentWeatherResponse?

// Unit test using :
// JUnit 5 -> @Test - Run method when I run ./mvnw test
// Mockito -> @Mock @InjectMocks when(...).thenReturn(...) - creates fake/mock dependencies
// AssertJ -> assertThat(thingIActuallyGot).shouldMatchThisCondition(...) - readable assertions

// Tell JUnit 5 to enable Mockito support for this test class
@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    // Create fake object
    @Mock
    private WeatherApiClient weatherApiClient;

    // Creates the real class being tested and injects fake objects into it
    @InjectMocks
    private WeatherService weatherService;

    @Test // Run this method as a test when ./mvnw test is executed
    void getCurrentWeatherMapsWeatherApiResponseToCurrentWeatherResponse() {
        // Create fake API data
        WeatherApiCurrentResponse apiResponse = buildWeatherApiResponse();
        // Tell the mock client to return it if this is done :
        when(weatherApiClient.getCurrentWeatherByLocation("London"))
                .thenReturn(apiResponse);

        // Run the real service method
        CurrentWeatherResponse response = weatherService.getCurrentWeather("London");

        // Check the service mapped the WeatherAPI DTO into our own response DTO (CurrentWeatherResponse)
        assertThat(response.location()).isEqualTo("London");
        assertThat(response.country()).isEqualTo("United Kingdom");
        assertThat(response.localTime()).isEqualTo("2026-07-14 20:00");
        assertThat(response.tempC()).isEqualTo(22.5);
        assertThat(response.feelsLikeC()).isEqualTo(23.0);
        assertThat(response.humidity()).isEqualTo(60);
        assertThat(response.condition()).isEqualTo("Partly Cloudy");
    }

    @Test
    void getCurrentWeatherPropagatesClientException() {
        // Make the fake client fail like the real API might fail
        when(weatherApiClient.getCurrentWeatherByLocation("London"))
                .thenThrow(new ResourceAccessException("Weather API unavailable"));

        // Means checks an exception
        // Run the service method, then check the exception type (if there is one like there should be)
        // It is expecting a ResourceAccessException, if it doesn't receive, it fails.
        // Next it checks the exception message, if it doesn't receive what it is expecting, it fails
        // If both expected were returned, test passes!
        assertThatThrownBy(() -> weatherService.getCurrentWeather("London"))
                .isInstanceOf(ResourceAccessException.class)
                .hasMessage("Weather API unavailable");
    }

    // Helper method used only by the test
    // Creates a fake WeatherApiCurrentResponse object
    private WeatherApiCurrentResponse buildWeatherApiResponse() {
        // Fake location section from WeatherAPI.com response
        WeatherApiLocation location = new WeatherApiLocation();
        location.setName("London");
        location.setCountry("United Kingdom");
        location.setLocalTime("2026-07-14 20:00");

        // Fake condition section from WeatherAPI.com response
        WeatherApiCondition condition = new WeatherApiCondition();
        condition.setText("Partly Cloudy");

        // Fake current weather section from WeatherAPI.com response
        WeatherApiCurrent current = new WeatherApiCurrent();
        current.setTempC(22.5);
        current.setFeelsLikeC(23.0);
        current.setHumidity(60);
        current.setCondition(condition);

        // Fake full WeatherAPI.com response.
        WeatherApiCurrentResponse response = new WeatherApiCurrentResponse();
        response.setLocation(location);
        response.setCurrent(current);

        return response;
    }
}
