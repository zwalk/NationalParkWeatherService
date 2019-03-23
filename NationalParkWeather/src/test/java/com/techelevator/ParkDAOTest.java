package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.JDBC.JDBCParkDAO;
import com.techelevator.JDBC.ParkDAO;
import com.techelevator.npgeek.model.Park;

import java.util.List;

import org.junit.*;

public class ParkDAOTest extends DAOIntegrationTest {
	
	private JdbcTemplate jdbcTemplate;
	private ParkDAO parkDao;
	private Park testPark;
	private static final String TEST_PARK_CODE = "ZACH";
	private static final String TEST_PARK_NAME = "Zach Park";
	private static final String TEST_PARK_STATE = "Ohio";
	private static final int TEST_PARK_ACREAGE = 100;
	private static final int TEST_PARK_ELEVATION_IN_FEET = 1000;
	private static final double TEST_PARK_MILES_OF_TRAIL = 20;
	private static final int TEST_PARK_NUMBER_OF_CAMPSITES = 2;
	private static final String TEST_PARK_CLIMATE = "Hot";
	private static final int TEST_PARK_YEAR_FOUNDED = 1776;
	private static final int TEST_PARK_ANNUAL_VIS_COUNT = 1;
	private static final String TEST_PARK_INSPIRATIONAL_QUOTE = "DON\'T LET YOUR DREAMS BE DREAMS!!!!!";
	private static final String TEST_PARK_INSPIRATIONAL_QUOTE_SOURCE = "Shia LaBeouf";
	private static final String TEST_PARK_DESCRIPTION = "Real nice park";
	private static final int TEST_PARK_ENTRY_FEE = 1;
	private static final int TEST_PARK_NUM_OF_ANIMAL_SPECIES = 1;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		parkDao = new JDBCParkDAO(getDataSource());
		testPark = new Park();
		setParkAttributes();
		insertParkIntoDatabase(testPark);
	}
	
	@Test
	public void get_all_parks_returns_all_parks() {
		
		List<Park> allParkList = parkDao.getAllParks();
		
		Assert.assertTrue(allParkList.contains(testPark));
			
	}
	
	@Test
	public void get_park_by_id_gets_test_park() {
		
		Park park = parkDao.getParkByCode(TEST_PARK_CODE);
		
		Assert.assertTrue(park.equals(testPark));
		
	}
	
	private void insertParkIntoDatabase(Park park) {
		
		String sqlInsertPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, " + 
				"inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlInsertPark, park.getCode(), park.getName(), park.getState(), park.getAcreage(), park.getElevationInFeet()
				, park.getMilesOfTrail(), park.getNumberOfCampsites(), park.getClimate(), park.getYearFounded(), park.getAnnualVisitorCount()
				, park.getInspirationalQuote(), park.getInspirationalQuoteSource(), park.getParkDescription(), park.getEntryFee(), park.getNumberOfAnimalSpecies());
	}
	
	private void setParkAttributes() {
		testPark.setAcreage(TEST_PARK_ACREAGE);
		testPark.setAnnualVisitorCount(TEST_PARK_ANNUAL_VIS_COUNT);
		testPark.setClimate(TEST_PARK_CLIMATE);
		testPark.setCode(TEST_PARK_CODE);
		testPark.setElevationInFeet(TEST_PARK_ELEVATION_IN_FEET);
		testPark.setEntryFee(TEST_PARK_ENTRY_FEE);
		testPark.setInspirationalQuote(TEST_PARK_INSPIRATIONAL_QUOTE);
		testPark.setInspirationalQuoteSource(TEST_PARK_INSPIRATIONAL_QUOTE_SOURCE);
		testPark.setMilesOfTrail(TEST_PARK_MILES_OF_TRAIL);
		testPark.setName(TEST_PARK_NAME);
		testPark.setNumberOfAnimalSpecies(TEST_PARK_NUM_OF_ANIMAL_SPECIES);
		testPark.setNumberOfCampsites(TEST_PARK_NUMBER_OF_CAMPSITES);
		testPark.setParkDescription(TEST_PARK_DESCRIPTION);
		testPark.setState(TEST_PARK_STATE);
		testPark.setYearFounded(TEST_PARK_YEAR_FOUNDED);
	}

}
