package com.example.thoitiet33;

public class WeatherData {
    private int temperature; // Nhiệt độ
    private int humidity;    // Độ ẩm
    private int cloudiness;  // Mây phủ (%)
    private int windSpeed;   // Tốc độ gió (km/h)

    // Constructor rỗng được yêu cầu cho Firebase
    public WeatherData() {
    }

    // Constructor với các tham số
    public WeatherData(int temperature, int humidity, int cloudiness, int windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
    }

    // Getters và Setters
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
}
