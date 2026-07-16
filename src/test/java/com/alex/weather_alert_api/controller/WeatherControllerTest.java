package com.alex.weather_alert_api.controller;

import com.alex.weather_alert_api.dto.response.CurrentWeatherResponse;
import com.alex.weather_alert_api.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Controller test for WeatherController using Spring MVC, MockMvc, Mockito and JUnit

// WebMvcTest also configures the Spring MVC test infrastructure, including a MockMvc object.
@WebMvcTest(WeatherController.class) // Start only the Spring MVC/controller slice of the app
public class WeatherControllerTest { // (@SpringBootTest is overkill and would start all of it)

    // MockMvc lets us call controller endpoints without starting a real server
    @Autowired
    private MockMvc mockMvc;

    // Fake service so this test only checks the controller layer
    @MockitoBean
    private WeatherService weatherService;

    @Test
    void getCurrentWeatherReturnsWeatherResponse() throws Exception {
        // Fake what the service should return for London
        CurrentWeatherResponse response = new CurrentWeatherResponse(
                "London",
                "United Kingdom",
                "2026-07-14 20:00",
                22.5,
                23.0,
                60,
                "Partly Cloudy"
        );

        when(weatherService.getCurrentWeather("London"))
                .thenReturn(response);

        // Call the endpoint and check the HTTP response JSON
        mockMvc.perform(get("/api/weather/current")
                        .param("location", "London"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("London"))
                .andExpect(jsonPath("$.country").value("United Kingdom"))
                .andExpect(jsonPath("$.localTime").value("2026-07-14 20:00"))
                .andExpect(jsonPath("$.tempC").value(22.5))
                .andExpect(jsonPath("$.feelsLikeC").value(23.0))
                .andExpect(jsonPath("$.humidity").value(60))
                .andExpect(jsonPath("$.condition").value("Partly Cloudy"));
    }
}
