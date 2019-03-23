package com.techelevator.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;

@Component
public class JDBCParkDAO implements ParkDAO {

	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public List<Park> getAllParks() {
		String sqlGetAllParks = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, " + 
				"inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park ORDER BY parkname ASC";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		
		List<Park> allParkList = new ArrayList<>();
		
		while (result.next()) {
			Park park = mapRowToPark(result);
			allParkList.add(park);
		}
		return allParkList;
	}

	@Override
	public Park getParkByCode(String parkCode) {
		
		String sqlGetParkByCode = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, " + 
				"inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park WHERE parkcode = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetParkByCode, parkCode);
		
		result.next();
		Park park = mapRowToPark(result);
		
		
		return park;
	}
	
	private Park mapRowToPark(SqlRowSet result) {
		Park park = new Park();
		
		park.setAcreage(result.getInt("acreage"));
		park.setAnnualVisitorCount(result.getInt("annualvisitorcount"));
		park.setClimate(result.getString("climate"));
		park.setCode(result.getString("parkcode"));
		park.setElevationInFeet(result.getInt("elevationinfeet"));
		park.setEntryFee(result.getInt("entryfee"));
		park.setInspirationalQuote(result.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(result.getString("inspirationalquotesource"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setName(result.getString("parkname"));
		park.setNumberOfAnimalSpecies(result.getInt("numberofanimalspecies"));
		park.setNumberOfCampsites(result.getInt("numberofcampsites"));
		park.setParkDescription(result.getString("parkdescription"));
		park.setState(result.getString("state"));
		park.setYearFounded(result.getInt("yearfounded"));
		
		return park;
	}

}
