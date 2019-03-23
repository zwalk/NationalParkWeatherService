package com.techelevator.npgeek.model;

public class Park {
	
	private String code;
	private String name;
	private String state;
	private int acreage;
	private int elevationInFeet;
	private double milesOfTrail;
	private int numberOfCampsites;
	private String climate;
	private int yearFounded;
	private int annualVisitorCount;
	private String inspirationalQuote;
	private String inspirationalQuoteSource;
	private String parkDescription;
	private int entryFee;
	private int numberOfAnimalSpecies;
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAcreage() {
		return acreage;
	}

	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}

	public int getElevationInFeet() {
		return elevationInFeet;
	}

	public void setElevationInFeet(int elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
	}

	public double getMilesOfTrail() {
		return milesOfTrail;
	}

	public void setMilesOfTrail(double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}

	public int getNumberOfCampsites() {
		return numberOfCampsites;
	}

	public void setNumberOfCampsites(int numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public int getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}

	public int getAnnualVisitorCount() {
		return annualVisitorCount;
	}

	public void setAnnualVisitorCount(int annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}

	public String getInspirationalQuote() {
		return inspirationalQuote;
	}

	public void setInspirationalQuote(String inspirationalQuote) {
		this.inspirationalQuote = inspirationalQuote;
	}

	public String getInspirationalQuoteSource() {
		return inspirationalQuoteSource;
	}

	public void setInspirationalQuoteSource(String inspirationalQuoteSource) {
		this.inspirationalQuoteSource = inspirationalQuoteSource;
	}

	public String getParkDescription() {
		return parkDescription;
	}

	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}

	public int getNumberOfAnimalSpecies() {
		return numberOfAnimalSpecies;
	}

	public void setNumberOfAnimalSpecies(int numberOfAnimalSpecies) {
		this.numberOfAnimalSpecies = numberOfAnimalSpecies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acreage;
		result = prime * result + annualVisitorCount;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + elevationInFeet;
		result = prime * result + entryFee;
		result = prime * result + ((inspirationalQuote == null) ? 0 : inspirationalQuote.hashCode());
		result = prime * result + ((inspirationalQuoteSource == null) ? 0 : inspirationalQuoteSource.hashCode());
		long temp;
		temp = Double.doubleToLongBits(milesOfTrail);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numberOfAnimalSpecies;
		result = prime * result + numberOfCampsites;
		result = prime * result + ((parkDescription == null) ? 0 : parkDescription.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + yearFounded;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Park other = (Park) obj;
		if (acreage != other.acreage)
			return false;
		if (annualVisitorCount != other.annualVisitorCount)
			return false;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (elevationInFeet != other.elevationInFeet)
			return false;
		if (entryFee != other.entryFee)
			return false;
		if (inspirationalQuote == null) {
			if (other.inspirationalQuote != null)
				return false;
		} else if (!inspirationalQuote.equals(other.inspirationalQuote))
			return false;
		if (inspirationalQuoteSource == null) {
			if (other.inspirationalQuoteSource != null)
				return false;
		} else if (!inspirationalQuoteSource.equals(other.inspirationalQuoteSource))
			return false;
		if (Double.doubleToLongBits(milesOfTrail) != Double.doubleToLongBits(other.milesOfTrail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfAnimalSpecies != other.numberOfAnimalSpecies)
			return false;
		if (numberOfCampsites != other.numberOfCampsites)
			return false;
		if (parkDescription == null) {
			if (other.parkDescription != null)
				return false;
		} else if (!parkDescription.equals(other.parkDescription))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (yearFounded != other.yearFounded)
			return false;
		return true;
	}

}
