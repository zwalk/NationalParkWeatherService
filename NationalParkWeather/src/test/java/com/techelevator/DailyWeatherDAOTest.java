package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.JDBC.DailyWeatherDAO;
import com.techelevator.JDBC.JDBCDailyWeatherDAO;
import com.techelevator.npgeek.model.DailyWeather;

import java.util.List;

import org.junit.*;

public class DailyWeatherDAOTest extends DAOIntegrationTest {
	
	private JdbcTemplate jdbcTemplate;
	private DailyWeatherDAO dailyWeatherDao;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dailyWeatherDao = new JDBCDailyWeatherDAO(getDataSource());
		clearWeatherTable();
	}
	
	@Test
	public void get_forecast_returns_correct_number_of_daily_weather_objects() {
		insertDailyForecastIntoDatabase();
		insertSecondDailyForecastIntoDatabase();
		List<DailyWeather> forecast = dailyWeatherDao.getFiveDayForecast("ENP");
		Assert.assertTrue(forecast.size() == 2);
	}
	
	@Test
	public void get_forecast_returns_0_daily_weather_objects() {
		insertDailyForecastIntoDatabase();
		insertSecondDailyForecastIntoDatabase();
		List<DailyWeather> forecast = dailyWeatherDao.getFiveDayForecast("GTNP");
		Assert.assertTrue(forecast.size() == 0);
	}
	
	@Test
	public void get_forecast_returns_correct_daily_weather_object() {
		insertDailyForecastIntoDatabase();
		insertSecondDailyForecastIntoDatabase();
		List<DailyWeather> forecast = dailyWeatherDao.getFiveDayForecast("ENP");
		Assert.assertEquals(100, forecast.get(0).getHighTempInFahrenheit());
		Assert.assertEquals(50, forecast.get(1).getLowTempInFahrenheit());
		Assert.assertEquals(2, forecast.get(1).getDayNumber());
	}

	private void clearWeatherTable() {
		String truncateWeatherTableSql = "TRUNCATE weather CASCADE";
		jdbcTemplate.update(truncateWeatherTableSql);
	}
	
	private void insertDailyForecastIntoDatabase() {
		String sqlInsertWeather = "INSERT INTO weather(parkcode, fivedayforecastvalue, low, high, forecast) " +
								"VALUES ('ENP', 1, 10, 100, 'There will be blood')";
		jdbcTemplate.update(sqlInsertWeather);
	}
	
	private void insertSecondDailyForecastIntoDatabase() {
		String sqlInsertWeather = "INSERT INTO weather(parkcode, fivedayforecastvalue, low, high, forecast) " +
								"VALUES ('ENP', 2, 50, 200, 'Feels like Ohio')";
		jdbcTemplate.update(sqlInsertWeather);
	}
	
	private void insertThirdDailyForecastIntoDatabase() {
		String sqlInsertWeather = "INSERT INTO weather(parkcode, fivedayforecastvalue, low, high, forecast) " +
								"VALUES ('GCNP', 1, 100, 120, 'Don't come here today!!')";
		jdbcTemplate.update(sqlInsertWeather);
	}
}