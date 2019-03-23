package com.techelevator.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.DailyWeather;


@Component
public class JDBCDailyWeatherDAO implements DailyWeatherDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCDailyWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<DailyWeather> getFiveDayForecast(String parkId) {
		List<DailyWeather> forecast = new ArrayList<DailyWeather>();
		String sqlWeatherResults = "SELECT  parkcode, fivedayforecastvalue, low, high, forecast " + 
								  "FROM weather " + 
								  "WHERE parkcode = ? " + 
								  "ORDER BY fivedayforecastvalue ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlWeatherResults, parkId);
		while (results.next()) {
			DailyWeather dailyWeather = mapRowToDailyWeather(results);
			forecast.add(dailyWeather);
		}
		
		return forecast;
	}
	
	private DailyWeather mapRowToDailyWeather(SqlRowSet result) {
		DailyWeather dailyWeather = new DailyWeather();
		
		dailyWeather.setParkCode(result.getString("parkcode"));
		dailyWeather.setDayNumber(result.getInt("fivedayforecastvalue"));
		dailyWeather.setHighTempInFahrenheit(result.getInt("high"));
		dailyWeather.setLowTempInFahrenheit(result.getInt("low"));
		dailyWeather.setForecast(result.getString("forecast"));
		
		return dailyWeather;
	}

}

