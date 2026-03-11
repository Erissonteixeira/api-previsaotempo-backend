CREATE TABLE meteorological_data (
    id BIGINT NOT NULL AUTO_INCREMENT,
    city VARCHAR(150) NOT NULL,
    forecast_date DATE NOT NULL,
    weather_day VARCHAR(50) NOT NULL,
    weather_night VARCHAR(50) NOT NULL,
    max_temperature DECIMAL(5,2) NOT NULL,
    min_temperature DECIMAL(5,2) NOT NULL,
    precipitation DECIMAL(5,2) NOT NULL,
    humidity DECIMAL(5,2) NOT NULL,
    wind_speed DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_meteorological_data_city_date UNIQUE (city, forecast_date)
);