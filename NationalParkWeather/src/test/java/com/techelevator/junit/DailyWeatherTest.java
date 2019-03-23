package com.techelevator.junit;

import org.junit.*;

import com.techelevator.npgeek.model.DailyWeather;

public class DailyWeatherTest {
	
	DailyWeather dailyWeather;
	
	@Before
	public void setup() {
		dailyWeather = new DailyWeather();
		dailyWeather.setDayNumber(1);
		dailyWeather.setHighTempInFahrenheit(86);
		dailyWeather.setLowTempInFahrenheit(50);
		dailyWeather.setParkCode("ZACH");
	}
	
	@Test
	public void get_low_temp_in_celsius_returns_correct_temp_in_celsius() {
		Assert.assertEquals(10, dailyWeather.getLowTempInCelsius());
	}
	
	@Test
	public void get_low_temp_in_celsius_rounds_up_when_needed() {
		
		DailyWeather testWeather = new DailyWeather();
		testWeather.setLowTempInFahrenheit(60);
		
		
		Assert.assertEquals(16, testWeather.getLowTempInCelsius());
	}
	
	@Test
	public void get_low_temp_in_celsius_rounds_down_when_needed() {
		
		DailyWeather testWeather = new DailyWeather();
		testWeather.setLowTempInFahrenheit(54);
		
		
		Assert.assertEquals(12, testWeather.getLowTempInCelsius());
	}
	
	@Test
	public void get_high_temp_in_celsius_returns_correct_temp_in_celsius() {
		Assert.assertEquals(30, dailyWeather.getHighTempInCelsius());
		
	}
	
	@Test
	public void get_high_temp_in_celsius_rounds_up_when_needed() {
		
		DailyWeather testWeather = new DailyWeather();
		testWeather.setHighTempInFahrenheit(60);
		
		
		Assert.assertEquals(16, testWeather.getHighTempInCelsius());
	}
	
	@Test
	public void get_high_temp_in_celsius_rounds_down_when_needed() {
		
		DailyWeather testWeather = new DailyWeather();
		testWeather.setHighTempInFahrenheit(54);
		
		
		Assert.assertEquals(12, testWeather.getHighTempInCelsius());
	}
	

}
