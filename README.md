# Weather Alert API

A Spring Boot REST API that fetches current weather data from WeatherAPI and returns a simplified response for clients.

This project was built to practise external API integration, DTO mapping, validation, structured error responses, and layered Spring Boot architecture.

## Tech Stack

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Validation
- Jackson
- Lombok
- Maven
- H2 Database

## Features

- Fetch current weather by location
- Call the external WeatherAPI current weather endpoint
- Map third-party API responses into internal DTOs
- Return a clean client-facing weather response
- Validate request parameters
- Return structured JSON error responses
- Handle external API and connection failures

## API Endpoint

### Get Current Weather

```http
GET /api/weather/current?location=London
```

Example response:

```json
{
  "location": "London",
  "country": "United Kingdom",
  "localTime": "2026-07-08 20:23",
  "tempC": 29.1,
  "feelsLikeC": 29.6,
  "humidity": 43,
  "condition": "Sunny"
}
```

## Environment Variables

Create a local `.env` file based on `.env.example`.

```env
WEATHER_API_KEY=your-weatherapi-key
```

Do not commit `.env`.

The application reads the key through:

```yaml
weather:
  api:
    key: ${WEATHER_API_KEY}
```

## Running Locally

Load your environment variables, or configure them in your IDE run configuration:

```bash
set -a
source .env
set +a
```

Run the app:

```bash
./mvnw spring-boot:run
```

The API runs on:

```text
http://localhost:8081
```

Example request:

```bash
curl "http://localhost:8081/api/weather/current?location=London"
```

## Error Handling

Errors are returned in a structured format:

```json
{
  "timestamp": "2026-07-10T00:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "location is required",
  "path": "/api/weather/current"
}
```

## Tests

Tests are planned for:

- Service-layer mapping logic
- Controller validation behavior
- Error response behavior

Run tests with:

```bash
./mvnw test
```

## Notes

This public version uses environment variables for secrets. The real WeatherAPI key is excluded from Git using `.gitignore`.
