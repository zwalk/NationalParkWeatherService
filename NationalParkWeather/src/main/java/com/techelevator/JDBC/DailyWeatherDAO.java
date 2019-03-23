package com.techelevator.JDBC;

import java.util.List;

import com.techelevator.npgeek.model.DailyWeather;

public interface DailyWeatherDAO {

	List<DailyWeather> getFiveDayForecast(String parkId);
	
}
