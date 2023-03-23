WindsurfingWeather App

This application uses the Weatherbit API to retrieve the 6-day weather forecast for 5 cities. 

Requirements

In order to use this app, you will need an API key from Weatherbit. Once you have your API key, you can replace the value of apiKey in the application.yml file with your key.
You also need apiBaseUrl in application.yml: https://api.weatherbit.io/v2.0/forecast/daily.

Usage

To run the app, simply execute the application. The app will retrieve the weather data for the following 5 cities:

   Jastarnia,
   Bridgetown,
   Forteleza,
   Pissouri,
   Le Morne.

The app will then filter the cities based on the following criteria:

    Temperature must be between 5 and 35 degrees Celsius
    Wind speed must be between 5 and 18 m/s

If multiple cities meet the filtering criteria, the app will calculate a score for each city using the following formula: temperature * 3 + wind speed. The city with the highest score will be displayed as the recommended city to windsurfing. If no city meets the filtering criteria, nothing will be displayed.
