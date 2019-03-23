package com.techelevator.npgeek.model;

public class DailyWeather {
	
	private String parkCode;
	private int dayNumber;
	private int lowTempInFahrenheit;
	private int highTempInFahrenheit;
	private String forecast;
	
	
	public int getLowTempInCelsius() {
		
		double lowTempAsDouble = ((lowTempInFahrenheit - 32) * (5.0/9.0));
		
		return (int)Math.round(lowTempAsDouble);
		
	}
	
	public int getHighTempInCelsius() {
		
		double highTempAsDouble = ((highTempInFahrenheit - 32) * (5.0/9.0));
		
		return (int)Math.round(highTempAsDouble);
		
	}
	
	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public int getLowTempInFahrenheit() {
		return lowTempInFahrenheit;
	}

	public void setLowTempInFahrenheit(int lowTempInFahrenheit) {
		this.lowTempInFahrenheit = lowTempInFahrenheit;
	}

	public int getHighTempInFahrenheit() {
		return highTempInFahrenheit;
	}

	public void setHighTempInFahrenheit(int highTempInFahrenheit) {
		this.highTempInFahrenheit = highTempInFahrenheit;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public String getAdvisory() {
		String advisory = "Weather Advisory: ";
		boolean isThereAnAdvisory = false;
		
		if(forecast.equals("snow")) {
			advisory += "Pack snowshoes. ";
			isThereAnAdvisory = true;
		} else if(forecast.equals("rain")) {
			advisory += "Pack raingear and wear water-proof shoes. ";
			isThereAnAdvisory = true;
		} else if(forecast.equals("thunderstorms")) {
			advisory += "Seek shelter and avoid hiking on exposed ridges. ";
			isThereAnAdvisory = true;
		} else if(forecast.equals("sun")) {
			advisory += "Pack sunblock. ";
			isThereAnAdvisory = true;
		} 
		
		if(highTempInFahrenheit > 75) {
			advisory += "Bring an extra gallon of water. ";
			isThereAnAdvisory = true;
		}
		
		if(Math.abs(highTempInFahrenheit - lowTempInFahrenheit) > 20) {
			advisory += "Wear breathable layers. ";
			isThereAnAdvisory = true;
		}
		
		if(lowTempInFahrenheit < 20) {
			advisory += "WARNING: Exposure to frigid temperatures could lead to death. ";
			isThereAnAdvisory = true;
		}
		
		if (! isThereAnAdvisory) {
			advisory += "None.";
		}
		
		return advisory;
	}
	
	
}
